package com.wy.yunoa.model.Resp;

import lombok.Data;

/**
 * @Author: wy
 * @CreateTime: 2023-10-29  23:28
 * @Description: TODO
 * @Version: 1.0
 */
@Data
public class UserInfoResp {
    /**
     *   private String name;
     *     private String avatar;
     *     private String introduction;
     *     private String[] roles;
     */
    private String name;
    private String avatar;
    private String introduction;
    private String[] roles;
}
