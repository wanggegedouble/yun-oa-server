package com.wy.yunoa.service;

import com.wy.yunoa.model.DTO.IndexLoginDTO;
import com.wy.yunoa.model.Resp.LoginResp;

/**
 * @Author: wy
 * @CreateTime: 2023-10-28  20:38
 * @Description: TODO
 * @Version: 1.0
 */
public interface SysLoginService {
    String login(IndexLoginDTO loginDTO);
}
