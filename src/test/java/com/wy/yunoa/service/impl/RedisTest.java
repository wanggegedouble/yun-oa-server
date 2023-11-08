package com.wy.yunoa.service.impl;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @Author: wy
 * @CreateTime: 2023-11-08  14:35
 * @Description: TODO
 * @Version: 1.0
 */
@SpringBootTest
public class RedisTest {

    @Resource
    private RedisTemplate<String,Object> redisTemplate;

    @Test
    public void test1() {
        String value = (String) redisTemplate.opsForValue().get("admin");
        System.out.println(value);
    }
}
