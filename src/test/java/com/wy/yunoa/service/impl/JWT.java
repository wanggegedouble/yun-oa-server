package com.wy.yunoa.service.impl;


import com.wy.yunoa.model.Stu;
import com.wy.yunoa.utils.JWT.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.Data;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


/**
 * @Author: wy
 * @CreateTime: 2023-11-01  00:56
 * @Description: TODO
 * @Version: 1.0
 */
public class JWT {
    public static void main(String[] args) {
//        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOjEsInVzZXJuYW1lIjoiYWRtaW4ifQ.r52VoZbLJ4lHmAbZMYiMOX0M-8MH2YzQb-SDvZ78hp4";
//        cn.hutool.jwt.JWT jwt = JWTUtil.parseToken(token);
//        JSONObject payloads = jwt.getPayloads();
//        List<String> list = new ArrayList<>();
//        JSONArray jsonArray = payloads.toJSONArray(list);
//        System.out.println(jsonArray);
//        Stu stu = new Stu("wy",12);
//        stu.show();
//        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//        String encode = encoder.encode("111111");
//        System.out.println(encode);
//        System.out.println(encoder.matches("111111", encode));
      String username = JwtUtil.getUsername("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOjEsInVzZXJuYW1lIjoiYWRtaW4iLCJqdGkiOiI5MDYzMmFlNC1mNzk5LTRmZGUtYmRjNi1iNjA0MzJlNWRiNmUiLCJleHAiOjE2OTk0NTE4NzQsImlhdCI6MTY5OTQ1MTgxNCwic3ViIjoiQVVUSC1VU0VSIiwiaXNzIjoid3kifQ.m7O9z7kgXazkA9LDfIrvNChTvelgil9FPyva80Z1u5Q");
        System.out.println(username);
    }
}

@Data
class Obj {
    private Long userId;
    private String username;
}