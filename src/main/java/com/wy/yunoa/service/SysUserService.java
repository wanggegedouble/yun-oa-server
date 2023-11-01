package com.wy.yunoa.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wy.yunoa.model.DTO.SysUserQueryDTO;
import com.wy.yunoa.model.Resp.RouterResp;
import com.wy.yunoa.model.Resp.SysUserResp;
import com.wy.yunoa.model.Resp.UserInfoResp;
import com.wy.yunoa.model.domain.SysUser;

import java.util.List;

/**
* @author huawei
* @description 针对表【sys_user(用户表)】的数据库操作Service
* @createDate 2023-10-22 00:17:51
*/
public interface SysUserService {

    List<SysUserResp> getList();

    SysUser getUserInfoById(Long userId);

    List<RouterResp> findUserMenuById(Long userId);

    List<String> findButtonsById(Long userId);

    Page<SysUserResp> selectList(Long page, Long limit, SysUserQueryDTO sysUserQueryDTO);
}
