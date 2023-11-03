package com.wy.yunoa.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wy.yunoa.model.DTO.SysAssginRoleVO;
import com.wy.yunoa.model.DTO.SysRolePageDTO;
import com.wy.yunoa.model.DTO.SysRoleSaveDTO;
import com.wy.yunoa.model.VO.SysRolePageVO;
import com.wy.yunoa.model.VO.SysRoleVO;
import com.wy.yunoa.model.domain.SysRole;

import java.util.List;
import java.util.Map;

/**
* @author huawei
* @description 针对表【sys_role(角色)】的数据库操作Service
* @createDate 2023-10-22 00:17:50
*/
public interface SysRoleService extends IService<SysRole> {

    List<SysRoleVO> selectList();

    Page<SysRolePageVO> selectPageList(Long page, Long limit, SysRolePageDTO sysRolePageDTO);

    void saveRole(SysRoleSaveDTO sysRoleSaveDTO);

    void doAssign(SysAssginRoleVO sysAssginRoleVO);

    Map<String, Object> toAssign(Long userId);
}
