package com.wy.yunoa.model.DTO;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * @Author: wy
 * @CreateTime: 2023-11-04  00:29
 * @Description: TODO
 * @Version: 1.0
 */
@Data
public class SysUserSaveDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = -4728203224542708953L;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 姓名
     */
    private String name;

    /**
     * 手机
     */
    private String phone;


}
