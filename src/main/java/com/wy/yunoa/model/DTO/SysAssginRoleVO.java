package com.wy.yunoa.model.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * @Author: wy
 * @CreateTime: 2023-11-04  01:19
 * @Description: TODO
 * @Version: 1.0
 */
@Data
public class SysAssginRoleVO implements Serializable {
    @Serial
    private static final long serialVersionUID = -631784115185811110L;

    @Schema(title = "用户Id")
    private Long id;
    @Schema(title = "角色列表")
    private List<Long> roleList;
}
