package com.wy.yunoa.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wy.yunoa.model.Resp.SysRoleResp;
import com.wy.yunoa.model.domain.SysRole;

import java.util.List;

/**
* @author huawei
* @description 针对表【sys_role(角色)】的数据库操作Service
* @createDate 2023-10-22 00:17:50
*/
public interface SysRoleService extends IService<SysRole> {

    List<SysRoleResp> selectList();
}
