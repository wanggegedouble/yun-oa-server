package com.wy.yunoa;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.wy.yunoa.mapper")
public class YunOaApplication {

    public static void main(String[] args) {
        SpringApplication.run(YunOaApplication.class, args);
    }

}
