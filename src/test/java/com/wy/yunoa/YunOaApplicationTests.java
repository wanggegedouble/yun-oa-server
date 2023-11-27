package com.wy.yunoa;

import cn.hutool.jwt.JWTUtil;
import com.wy.yunoa.service.HelloService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.Serial;
import java.util.HashMap;
import java.util.Map;


@Slf4j
@SpringBootTest
public class YunOaApplicationTests {

    @Resource(name = "helloServiceA")
    private HelloService helloService;
    @Value("${jwt.key}")
    private String key;
    @Value("${jwt.expire_time}")
    private Long expire_time;

    @Test
    public void contextLoads() {
        helloService.sayHello();
    }

    @Test
    public void test() {
        System.out.println("Hello World!");
    }

    @Test
    public void JwtTest() {
        Map<String,Object> map = new HashMap<>() {
            @Serial
            private static final long serialVersionUID = 161078396661764878L;
            {
                put("username","wangyao");
                put("userId","12345");
                put("expire_time",System.currentTimeMillis() + expire_time);
            }
        };
        String token = JWTUtil.createToken(map, key.getBytes());
        log.info("token:{}",token);

        String userId = (String) JWTUtil.parseToken(token).getPayload("userId");
        log.info("userId:{}",userId);
    }


}
