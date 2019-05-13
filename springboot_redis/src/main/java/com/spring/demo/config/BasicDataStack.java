package com.spring.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
/**
 * description
 * @author zhangyang
 * @version 0.1
 * create 2019/2/20
 */
@Component
public class BasicDataStack implements CommandLineRunner {

  @Autowired
  RedisTemplate<Object, Object> redisTemplate;

  @Override
  public void run(String... args) throws Exception {
    redisTemplate.opsForValue().set("test", "11111");
    System.out.println("111111111111111111");
    System.out.println(redisTemplate.opsForValue().get("test"));

  }

}
