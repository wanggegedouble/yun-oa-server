package com.wy.yunoa.utils.JWT;

import cn.hutool.jwt.JWT;
import lombok.Data;

import java.util.Date;

/**
 * @Author: wy
 * @CreateTime: 2023-11-01  01:03
 * @Description: TODO
 * @Version: 1.0
 */
@Data
public class JWTHpler {
    // 过期时间
    private static final long TOKEN_EXP = 6*3600*1000L;
    // tokenKey
    private static final String key = "wang";
    private static final long exprTime = 6*60*60*1000;

    public static String createToken(Long userId,String username) {
        Date now = new Date();
        JWT jwt = JWT.create()
                .setPayload("userId", 1)
                .setPayload("username", "wangyao")
                .setExpiresAt(new Date(now.getTime()+exprTime))
                .setKey(key.getBytes());
        return jwt.sign();
    }

    //验证是Token
    public static boolean verify(String token) {
        return JWT.of(token).setKey(key.getBytes()).validate(0);
    }

    public static Long getUserId(String token) {
        Object userId = JWT.of(token).getPayload("userId");
        return Long.parseLong(userId.toString());
    }

    public static String getUsername(String token) {
        return String.valueOf(JWT.of(token).getPayload("username"));
    }

}

