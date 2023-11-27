package com.wy.yunoa.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.io.Serial;

/**
 * @Author: wy
 * @CreateTime: 2023-10-22  17:30
 * @Description: TODO
 * @Version: 1.0
 */
@Getter
public class BaseRuntimeException extends RuntimeException{
    @Serial
    private static final long serialVersionUID = 2650129896289456984L;

    private Integer errorCode;

    public BaseRuntimeException(Integer errorCode,String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }


}
