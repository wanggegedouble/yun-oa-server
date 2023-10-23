package com.wy.yunoa.Result;

import lombok.Getter;

/**
 * @Author: wy
 * @CreateTime: 2023-10-22  15:39
 * @Description: TODO
 * @Version: 1.0
 */
@Getter
public enum ResultCodeEnum {

    SUCCESS(200,"请求成功");

    private final int value;
    private final String message;

    ResultCodeEnum(Integer value, String message) {
        this.value = value;
        this.message = message;
    }

}
