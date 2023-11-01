package com.wy.yunoa.service.impl;

import cn.hutool.jwt.JWT;

import java.util.Date;

/**
 * @Author: wy
 * @CreateTime: 2023-11-01  15:24
 * @Description: TODO
 * @Version: 1.0
 */
public class NewJWt {
    public static void main(String[] args) {
        byte[] v = "wang".getBytes();
        String token = createToken();
        System.out.println(token);
        pareToken(token,v);
    }

    private static void pareToken(String token,byte[] v) {
        boolean verify = JWT.of(token).setKey(v).validate(0);
        System.out.println("verify:"+verify);
        Object userId = JWT.of(token).getPayload("userId");
        System.out.println(Long.parseLong(userId.toString()));
        Object userId1 = JWT.of(token).getPayload("username");
        String s = String.valueOf(userId1);
        System.out.println(s);
    }

    public static String createToken() {
        byte[] key = "wang".getBytes();
        Date now = new Date();
        long exprTime = now.getTime()+(20*1000);
        JWT jwt = JWT.create()
                .setPayload("userId", 1)
                .setPayload("username", "wangyao")
                .setExpiresAt(new Date(exprTime))
                .setKey(key);
        return jwt.sign();
    }
}
