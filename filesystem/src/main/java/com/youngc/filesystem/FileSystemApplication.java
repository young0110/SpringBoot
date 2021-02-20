package com.youngc.filesystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.util.unit.DataSize;
import org.springframework.util.unit.DataUnit;
import javax.servlet.MultipartConfigElement;

@SpringBootApplication
public class FileSystemApplication {
    public static void main(String[] args) {
        SpringApplication.run(FileSystemApplication.class, args);
    }

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize(DataSize.of(20, DataUnit.MEGABYTES));
        factory.setMaxRequestSize(DataSize.of(30, DataUnit.MEGABYTES));
        // 文件临时保存目录 避免占用服务器 temp 目录 引起的异常
        factory.setLocation("/data/upload_tmp");
        // factory.setLocation("D://data");
        return factory.createMultipartConfig();
    }
}


