package com.wy.yunoa.Result;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.io.IOException;

/**
 * @Author: wy
 * @CreateTime: 2023-11-08  20:35
 * @Description: TODO
 * @Version: 1.0
 */
public class ResponseResult {

    public static void of(HttpServletResponse response,Result<Object> result) {
        ObjectMapper objectMapper = new ObjectMapper();
        response.setStatus(HttpStatus.OK.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        try {
            objectMapper.writeValue(response.getOutputStream(),result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}