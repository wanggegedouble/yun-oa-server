package com.wy.yunoa.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wy.yunoa.model.DTO.SysUserQueryDTO;
import com.wy.yunoa.model.DTO.SysUserSaveDTO;
import com.wy.yunoa.model.VO.RouterVO;
import com.wy.yunoa.model.VO.SysUserVO;
import com.wy.yunoa.model.domain.SysUser;

import java.util.List;

/**
* @author huawei
* @description 针对表【sys_user(用户表)】的数据库操作Service
* @createDate 2023-10-22 00:17:51
*/
public interface SysUserService {

    List<SysUserVO> getList();

    SysUser getUserInfoById(Long userId);

    List<RouterVO> findUserMenuById(Long userId);

    List<String> findButtonsById(Long userId);

    Page<SysUserVO> selectList(Long page, Long limit, SysUserQueryDTO sysUserQueryDTO);

    SysUserVO getUserById(Long id);

    void delUserById(Long id);

    void updateStatus(Long id, Integer status);

    void saveUser(SysUserSaveDTO sysUserSaveDTO);

    SysUser findUserByUsername(String username);
}
