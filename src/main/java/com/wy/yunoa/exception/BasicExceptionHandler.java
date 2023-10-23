package com.wy.yunoa.exception;

import com.wy.yunoa.Result.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Author: wy
 * @CreateTime: 2023-10-22  17:09
 * @Description: TODO
 * @Version: 1.0
 */
@RestControllerAdvice
public class BasicExceptionHandler {

    @ExceptionHandler(value = RuntimeException.class)
    public Result errorHandler(Exception exception) {
        return Result.of(500,exception.getMessage());
    }

    @ExceptionHandler(value = CustomException.class)
    public Result errorHandler(CustomException exception) {
        return Result.of(exception.getErrorCode(),exception.getMessage());
    }
}
