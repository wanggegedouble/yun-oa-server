package com.wy.yunoa.service.impl;

import cn.hutool.json.JSON;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import cn.hutool.jwt.JWTPayload;
import cn.hutool.jwt.JWTUtil;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: wy
 * @CreateTime: 2023-11-01  00:56
 * @Description: TODO
 * @Version: 1.0
 */
public class JWT {
    public static void main(String[] args) {
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOjEsInVzZXJuYW1lIjoiYWRtaW4ifQ.r52VoZbLJ4lHmAbZMYiMOX0M-8MH2YzQb-SDvZ78hp4";
        cn.hutool.jwt.JWT jwt = JWTUtil.parseToken(token);
        JSONObject payloads = jwt.getPayloads();
        List<String> list = new ArrayList<>();
        JSONArray jsonArray = payloads.toJSONArray(list);
        System.out.println(jsonArray);

    }
}

@Data
class Obj {
    private Long userId;
    private String username;
}