package com.wy.yunoa.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wy.yunoa.exception.CustomException;
import com.wy.yunoa.mapper.SysUserRoleMapper;
import com.wy.yunoa.model.DTO.SysAssginRoleVO;
import com.wy.yunoa.model.DTO.SysRolePageDTO;
import com.wy.yunoa.model.DTO.SysRoleSaveDTO;
import com.wy.yunoa.model.VO.SysRolePageVO;
import com.wy.yunoa.model.VO.SysRoleVO;
import com.wy.yunoa.model.domain.SysRole;
import com.wy.yunoa.mapper.SysRoleMapper;
import com.wy.yunoa.model.domain.SysUserRole;
import com.wy.yunoa.service.SysRoleService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.*;

/**
* @author huawei
* @description 针对表【sys_role(角色)】的数据库操作Service实现
* @createDate 2023-10-22 00:17:50
*/
@Service
@Slf4j
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

    @Resource
    private SysRoleMapper roleMapper;
    @Resource
    private SysUserRoleMapper userRoleMapper;

    @Override
    public List<SysRoleVO> selectList() {
        List<SysRole> sysRoles = roleMapper.selectList(null);
        if (Optional.ofNullable(sysRoles).isEmpty()) {
            throw new CustomException(400,"无数据");
        }
        return sysRoles.stream().map(sysRole -> {
            SysRoleVO resp = new SysRoleVO();
            BeanUtils.copyProperties(sysRole, resp);
            return resp;
        }).toList();
    }

    @Override
    public Page<SysRolePageVO> selectPageList(Long page, Long limit, SysRolePageDTO sysRolePageDTO) {
        Page<SysRole> pageParam = new Page<>(page,limit);
        LambdaQueryWrapper<SysRole> wrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotEmpty(sysRolePageDTO.getRoleName())) {
            wrapper.like(SysRole::getRoleName,sysRolePageDTO.getRoleName());
        }
        Page<SysRole> sysRolePage = this.roleMapper.selectPage(pageParam, wrapper);
        List<SysRole> records = sysRolePage.getRecords();
        if (Optional.ofNullable(records).isEmpty()) {
            throw new CustomException(400,"无数据");
        }
        List<SysRolePageVO> list = records.stream().map(sysRole -> {
            SysRolePageVO resPageVO = new SysRolePageVO();
            BeanUtils.copyProperties(sysRole, resPageVO);
            return resPageVO;
        }).toList();
        Page<SysRolePageVO> respPage = new Page<>();
        BeanUtils.copyProperties(sysRolePage,respPage);
        respPage.setRecords(list);
        return respPage;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveRole(SysRoleSaveDTO sysRoleSaveDTO) {
        SysRole sysRole = new SysRole();
        BeanUtils.copyProperties(sysRoleSaveDTO,sysRole);
        sysRole.setIsDeleted(0);
        int insert = this.roleMapper.insert(sysRole);
        if (insert != 1) {
            throw new CustomException(500,"添加失败");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void doAssign(SysAssginRoleVO sysAssginRoleVO) {
        // 删除用户之前已有的角色
        LambdaQueryWrapper<SysUserRole> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysUserRole::getUserId,sysAssginRoleVO.getId());
        int delete = this.userRoleMapper.delete(wrapper);
        if (delete != 1) {
            throw new CustomException(400,"清除失败");
        }
        List<Long> roleList = sysAssginRoleVO.getRoleList();
        for (Long role : roleList) {
            if (ObjectUtils.isEmpty(role)) {
                continue;
            }
            SysUserRole sysUserRole = new SysUserRole();
            sysUserRole.setRoleId(role);
            sysUserRole.setUserId(sysAssginRoleVO.getId());
            int insert = this.userRoleMapper.insert(sysUserRole);
            if (insert != 1) {
                throw new CustomException(500,"添加失败");
            }
        }
    }

    @Override
    public Map<String, Object> toAssign(Long userId) {
        Map<String,Object> map = new HashMap<>();
        // 查询所有角色
        List<SysUserRole> allRoleList = this.userRoleMapper.selectList(null);
        map.put("allRoleList",allRoleList);
        // 查询用户角色
        LambdaQueryWrapper<SysUserRole> userRoleWrapper = new LambdaQueryWrapper<>();
        userRoleWrapper.eq(SysUserRole::getUserId,userId);
        List<SysUserRole> userRoleList = this.userRoleMapper.selectList(userRoleWrapper);
        map.put("userRoleList",userRoleList);
        return map ;
    }
}




