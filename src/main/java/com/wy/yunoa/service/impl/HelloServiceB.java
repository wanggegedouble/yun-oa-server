package com.wy.yunoa.service.impl;

import com.wy.yunoa.service.HelloService;
import org.springframework.stereotype.Service;

/**
 * @Author: wy
 * @CreateTime: 2023-10-28  14:51
 * @Description: TODO
 * @Version: 1.0
 */
@Service
public class HelloServiceB implements HelloService {

    @Override
    public void sayHello() {
        System.out.println("HelloServiceB sayHello");
    }
}
