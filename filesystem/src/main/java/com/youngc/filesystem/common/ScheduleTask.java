package com.youngc.filesystem.common;

import com.youngc.filesystem.service.IFileUploadService;
import com.youngc.filesystem.service.impl.FileUploadServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Configuration
@EnableScheduling
public class ScheduleTask {

    @Autowired
    private IFileUploadService fileUploadService;

    @Scheduled(cron = "0 0 0 * * ?")
    private void checkExpiredFile() {
        log.info("清除调用次数MAP");
        FileUploadServiceImpl.callTimesMap.clear();
        log.info("清除过期文件");
        long curMillis = System.currentTimeMillis();
        Map<Long, String> expiredFileMap = new HashMap<>(FileUploadServiceImpl.expiredFileMap);
        for (Map.Entry<Long, String> expiredFile : expiredFileMap.entrySet()) {
            if (expiredFile.getKey() < curMillis) {
                fileUploadService.deleteFile(expiredFile.getValue());
                FileUploadServiceImpl.expiredFileMap.remove(expiredFile.getKey());
            }
        }
    }
}
