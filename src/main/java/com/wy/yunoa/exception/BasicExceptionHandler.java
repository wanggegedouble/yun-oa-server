package com.wy.yunoa.exception;

import com.wy.yunoa.Result.Result;
import com.wy.yunoa.Result.ResultCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Author: wy
 * @CreateTime: 2023-10-22  17:09
 * @Description: TODO
 * @Version: 1.0
 */
@RestControllerAdvice
@Slf4j
public class BasicExceptionHandler {

    @ExceptionHandler(value = RuntimeException.class)
    public Result errorHandler(RuntimeException exception) {
        log.error("exception",exception);
        return Result.of(ResultCodeEnum.SYSTEM_ERROR,exception.getMessage());
    }

    @ExceptionHandler(value = CustomException.class)
    public Result errorHandler(CustomException exception) {
        log.error("runtimeException",exception);
        return Result.of(ResultCodeEnum.SYSTEM_ERROR,exception.getMessage());
    }
}
