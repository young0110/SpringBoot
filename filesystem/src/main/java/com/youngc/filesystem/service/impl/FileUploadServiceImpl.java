package com.youngc.filesystem.service.impl;

import com.youngc.filesystem.common.ServiceException;
import com.youngc.filesystem.service.IFileUploadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

@Slf4j
@Service
public class FileUploadServiceImpl implements IFileUploadService {

    @Value("${file.dir}")
    private String uploadFileDir;
    @Value("${file.allowType}")
    private String allowFileType;
    @Value("${file.maxTimes}")
    private Integer allowMaxTime;
    @Value("${file.serverHost}")
    private String    serverHost;

    @Resource
    private HttpServletRequest request;

    private final SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");

    private final Random random = new Random();

    public static Map<String, Integer> callTimesMap = new HashMap<>();

    public static Map<Long, String> expiredFileMap = new HashMap<>();

    @Override
    public List<String> uploadFile(String prefix, List<MultipartFile> fileList, long expireTime, boolean flag) {
        List<String> pathList = new ArrayList<>(10);
        if (checkAllowIP() < 0) {
            throw new ServiceException("今日已经超过调用次数!");
        }
        prefix = !StringUtils.isEmpty(prefix) ? prefix : getFormat().format(new Date());
        String fileDir = uploadFileDir + File.separator + prefix;
        File dir = new File(fileDir);
        dir.setWritable(true, false);
        if (!dir.exists()) dir.mkdirs();
        int num = 0;
        for (MultipartFile file : fileList) {
            String originName = file.getOriginalFilename();
            if (checkAllowFile(originName) < 0) {
                throw new ServiceException("不支持文件类型!");
            }
            String suffix = originName.substring(originName.lastIndexOf("."));
            long currentTimeMillis = System.currentTimeMillis();
            String newFileName = prefix + File.separator + currentTimeMillis + (num++) + suffix;
            try {
                File newFile = new File(uploadFileDir + File.separator + newFileName);
                file.transferTo(newFile);
                log.info("保存文件[{}]成功！", newFileName);
                if (-1 != expireTime) {
                    expireTime = expireTime * 60 * 1000;
                    expiredFileMap.put(currentTimeMillis + expireTime, newFileName);
                }
                pathList.add(flag ? serverHost + newFileName : newFileName);
            }  catch (Exception e) {
                throw new ServiceException(e, "传输异常!");
            }
        }
        return pathList;
    }

    @Override
    public int deleteFile(String fileName) {
        String fullName = uploadFileDir + File.separator + fileName;
        File realFile = new File(fullName);
        if (realFile.exists()) {
            int code =  realFile.delete() ? 1 : 0;
            log.info("删除文件[{}]{}！", fileName, code == 1 ? "成功" : "失败");
            return code;
        }
        return 0;
    }

    private synchronized SimpleDateFormat getFormat() {
        return format;
    }

    private int checkAllowFile(String name) {
        if (null == name || !name.contains(".")) return -1;
        String suffix = name.substring(name.lastIndexOf(".") + 1);
        return allowFileType.indexOf(suffix.toUpperCase());
    }

    private int checkAllowIP() {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        log.info("Access IP: {}", ip);
        if (callTimesMap.containsKey(ip)) {
            int times = callTimesMap.get(ip);
            if (times + 1 > allowMaxTime) {
                return -1;
            } else {
                callTimesMap.put(ip, times + 1);
            }
        } else {
            callTimesMap.put(ip, 1);
        }
        return 1;
    }
}
