package com.wy.yunoa.Result;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * @Author: wy
 * @CreateTime: 2023-10-22  15:34
 * @Description: TODO
 * @Version: 1.0
 */
@Data
public class Result<T> implements Serializable {
    @Serial
    private static final long serialVersionUID = 3168263053740722164L;

    private Integer code;
    private String message;
    private T data;

    public static <T> Result<T> of(String message,T data) {
        return new Result<>(message,data);
    }
    public static <T> Result<T> of(Integer code,String message) {
        return new Result<>(code,message);
    }
    public static <T> Result<T> of(ResultCodeEnum resultCodeEnum,T data) {
        return new Result<>(resultCodeEnum.getMessage(), data);
    }

    // 成功
    public Result(String message,T data) {
        this.code = ResultCodeEnum.SUCCESS.getValue();
        this.data = data;
        this.setData(data);
        this.setMessage(message);
    }
    // 失败
    public Result(Integer code,String message) {
        this.code = code;
        this.message = message;
    }

    public Result(ResultCodeEnum resultCodeEnum,T data) {
        this.code = resultCodeEnum.getValue();
        this.message = resultCodeEnum.getMessage();
        this.setData(data);
    }

}
