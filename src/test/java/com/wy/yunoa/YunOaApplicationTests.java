package com.wy.yunoa;

import com.wy.yunoa.service.HelloService;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class YunOaApplicationTests {

    @Resource(name = "helloServiceA")
    private HelloService helloService;

    @Test
    void contextLoads() {
        helloService.sayHello();
    }

    @Test
    public void test() {
        System.out.println("Hello World!");
    }

}
