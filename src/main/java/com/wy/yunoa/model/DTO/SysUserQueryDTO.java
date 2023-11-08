package com.wy.yunoa.model.DTO;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * @Author: wy
 * @CreateTime: 2023-11-02  01:37
 * @Description: TODO
 * @Version: 1.0
 */
@Data
public class SysUserQueryDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 3653841516412031481L;

    private String keyword;

    private Date createTimeBegin;

    private Date createTimeEnd;
}
