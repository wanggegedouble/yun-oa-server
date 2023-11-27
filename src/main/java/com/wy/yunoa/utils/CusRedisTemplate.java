package com.wy.yunoa.utils;

import jakarta.annotation.Resource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @Author: wy
 * @CreateTime: 2023-11-10  10:20
 * @Description: TODO
 * @Version: 1.0
 */
@Component
public class CusRedisTemplate {
    @Resource
    private RedisTemplate<Object,Object>  redisTemplate;


    public boolean set(String key,String value) {
        boolean result = false;
        redisTemplate.opsForValue().set(key,value);
        return result;
    }
}
