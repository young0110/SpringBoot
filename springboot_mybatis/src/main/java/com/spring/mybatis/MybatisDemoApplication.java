package com.spring.mybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.spring.mybatis.mapper")
@SpringBootApplication
public class MybatisDemoApplication {

  public static void main(String[] args) {
    SpringApplication.run(MybatisDemoApplication.class, args);
  }

}
