package com.wy.yunoa.exception;

import java.io.Serial;

/**
 * @Author: wy
 * @CreateTime: 2023-10-22  17:37
 * @Description: TODO
 * @Version: 1.0
 */
public class CustomException extends BaseRuntimeException{

    @Serial
    private static final long serialVersionUID = 7834571725428505004L;

    private Integer code;

    public CustomException(Integer errorCode, String message) {
        super(errorCode, message);
    }
}
