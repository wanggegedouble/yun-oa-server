package com.wy.yunoa.service.impl;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Set;


/**
 * @Author: wy
 * @CreateTime: 2023-11-03  16:25
 * @Description: TODO
 * @Version: 1.0
 */
@SpringBootTest
public class SpringJDBCTest {
    @Resource
    private RedisTemplate<String,String> redisTemplate;


    @Test
    public void list() {
        redisTemplate.opsForValue().set("name","wy");
        Set<String> keys = redisTemplate.keys("*");
        System.out.println(keys);
    }
}
