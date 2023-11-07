package com.wy.yunoa.model.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

/**
 * @Author: wy
 * @CreateTime: 2023-11-05  05:46
 * @Description: TODO
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysRole_id_name implements Serializable {
    @Serial
    private static final long serialVersionUID = -4595969739477816333L;
    private Long id;
    private String roleName;
}
