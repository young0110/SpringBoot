package com.youngc.redis.components;

import com.youngc.redis.model.LoginInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import java.util.*;

/**
 * description
 * @author zhangyang
 * @version 0.1
 * create 2019/2/20
 */
@Slf4j
@Component
public class BasicDataStack implements CommandLineRunner {

  @Autowired
  RedisTemplate<String, Object> redisTemplate;

  @Autowired
  JedisPool jedisPool;

  @Override
  public void run(String... args) throws Exception {
    jedisTest();
    redisTemplateTest();
  }

  private void jedisTest () {
    // jedis pool
    Jedis jedis = jedisPool.getResource();
    // key: value
    jedis.set("login-user", "admin:1s3e-4r5H-xl43-00Jd");
    log.info("login-user: {}", jedis.get("login-user"));
    // key: list
    jedis.lpush("roles", "common", "admin", "super");
    List<String> roles = jedis.lrange("roles", 0, 2);
    log.info("roles: {}", roles);
    // 获取数据并输出
    Set<String> keys = jedis.keys("*");
    keys.forEach(it -> log.info("key: {}", it));
  }

  private void redisTemplateTest () {
    LoginInfo info = new LoginInfo();
    info.setUserName("admin");
    info.setAge(21);
    info.setLoginTime(new Date());
    info.setRole(0);
    redisTemplate.opsForValue().set("session-id:1s3e-4r5H-xl43-00Jd", info);
    LoginInfo loginInfo = (LoginInfo) redisTemplate.opsForValue().get("session-id:1s3e-4r5H-xl43-00Jd");
    log.info("LoginInfo: {}", loginInfo);

    Map map = new HashMap();
    map.put("a", 1);
    map.put("b", 2);
    map.put("c", 3);
    HashOperations<String, String, Object> hashOperations = redisTemplate.opsForHash();
    hashOperations.putAll("map", map);
    log.info("map->a: {}", hashOperations.getOperations().opsForHash().get("map", "a"));
  }

}
