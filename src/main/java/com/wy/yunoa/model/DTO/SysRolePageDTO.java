package com.wy.yunoa.model.DTO;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * @Author: wy
 * @CreateTime: 2023-11-04  00:59
 * @Description: TODO
 * @Version: 1.0
 */
@Data
public class SysRolePageDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = -7004754640181757585L;

    private String roleName;
}
