package com.spring.demo.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Date;

/**
 * description
 * @author zhangyang
 * @version 0.1
 * create 2019/2/20
 */
@Component
public class BasicDataStack implements CommandLineRunner {

  @Autowired
  RedisTemplate<String, Object> redisTemplate;

  @Autowired
  JedisPool jedisPool;

  @Override
  public void run(String... args) throws Exception {
    LoginInfo info = new LoginInfo();
    info.setUserName("admin");
    info.setAge(21);
    info.setLoginTime(new Date());
    info.setRole(0);
    //ObjectMapper objectMapper = new ObjectMapper();
    //redisTemplate.opsForValue().set("session-id-1s3e-4r5H-xl43-00Jd", objectMapper.writeValueAsString(info));
    redisTemplate.opsForValue().set("session-id:1s3e-4r5H-xl43-00Jd", info);
    LoginInfo loginInfo = (LoginInfo) redisTemplate.opsForValue().get("session-id:1s3e-4r5H-xl43-00Jd");

    //jedis pool
    Jedis jedis = jedisPool.getResource();
    jedis.set("login-user", "admin:1s3e-4r5H-xl43-00Jd");

    System.out.println(redisTemplate.opsForValue().get("session-id:1s3e-4r5H-xl43-00Jd"));
    System.out.println(jedis.get("login-user"));
  }

}
