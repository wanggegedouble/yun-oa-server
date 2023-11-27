package com.wy.yunoa.service.impl;

import org.apache.commons.lang3.StringUtils;

/**
 * @Author: wy
 * @CreateTime: 2023-11-01  23:31
 * @Description: TODO
 * @Version: 1.0
 */
public class Main {
    public static void main(String[] args) {
        String hello = StringUtils.abbreviate("hello", 6);
        int length = hello.length();
        System.out.println(hello);
        System.out.println(length);
    }
}
