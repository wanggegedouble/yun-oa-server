package com.wy.yunoa.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wy.yunoa.exception.CustomException;
import com.wy.yunoa.model.Resp.SysRoleResp;
import com.wy.yunoa.model.domain.SysRole;
import com.wy.yunoa.mapper.SysRoleMapper;
import com.wy.yunoa.service.SysRoleService;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
* @author huawei
* @description 针对表【sys_role(角色)】的数据库操作Service实现
* @createDate 2023-10-22 00:17:50
*/
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

    @Resource
    private SysRoleMapper roleMapper;

    @Override
    public List<SysRoleResp> selectList() {
        List<SysRole> sysRoles = roleMapper.selectList(null);
        if (Optional.ofNullable(sysRoles).isEmpty()) {
            throw new CustomException(400,"无数据");
        }
        return sysRoles.stream().map(sysRole -> {
            SysRoleResp resp = new SysRoleResp();
            BeanUtils.copyProperties(sysRole, resp);
            return resp;
        }).toList();
    }
}




