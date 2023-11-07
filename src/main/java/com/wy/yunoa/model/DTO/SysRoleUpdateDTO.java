package com.wy.yunoa.model.DTO;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * @Author: wy
 * @CreateTime: 2023-11-05  06:45
 * @Description: TODO
 * @Version: 1.0
 */
@Data
public class SysRoleUpdateDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = -303681751177848116L;
    private Long id;
    private String roleCode;
    private String roleName;
}
