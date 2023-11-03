package com.wy.yunoa.model.DTO;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * @Author: wy
 * @CreateTime: 2023-11-04  01:08
 * @Description: TODO
 * @Version: 1.0
 */
@Data
public class SysRoleSaveDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 2546897284796610034L;


    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 角色编码
     */
    private String roleCode;

    /**
     * 描述
     */
    private String description;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;
}
