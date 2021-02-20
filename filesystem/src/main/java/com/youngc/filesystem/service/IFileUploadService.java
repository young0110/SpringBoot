package com.youngc.filesystem.service;

import org.springframework.web.multipart.MultipartFile;
import java.util.List;

public interface IFileUploadService {

    /**
     * 上传文件
     * @param prefix 文件夹
     * @param fileList 文件列表
     * @param expireTime 过期时间 定时删除 永久保存 -1
     * @param flag 是否返回完整路径
     * @return 文件路径
     */
    List<String> uploadFile(String prefix, List<MultipartFile> fileList, long expireTime, boolean flag);

    /**
     * 删除文件
     * @param fileName 文件名
     * @return code
     */
    int deleteFile(String fileName);

}
