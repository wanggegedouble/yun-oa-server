package com.wy.yunoa.model.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @Author: wy
 * @CreateTime: 2023-10-28  20:40
 * @Description: TODO
 * @Version: 1.0
 */
@Data
public class IndexLoginDTO {
    @NotNull(message = "用户名不能为空")
    private String username;
    @NotNull(message = "密码不能为空")
    private String password;
}
