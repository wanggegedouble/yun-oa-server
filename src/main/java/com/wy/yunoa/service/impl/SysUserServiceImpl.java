package com.wy.yunoa.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wy.yunoa.model.Resp.SysUserResp;
import com.wy.yunoa.model.domain.SysUser;
import com.wy.yunoa.mapper.SysUserMapper;
import com.wy.yunoa.service.SysUserService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
* @author huawei
* @description 针对表【sys_user(用户表)】的数据库操作Service实现
* @createDate 2023-10-22 00:17:51
*/
@Service
@Slf4j
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Resource
    private SysUserMapper userMapper;

    @Override
    public List<SysUserResp> getList() {
        List<SysUser> sysUsers = userMapper.selectList(null);
        if (Objects.isNull(sysUsers)) {
            //TODO
            log.info("");
        }
        return sysUsers.stream().map(user -> {
            SysUserResp resp = new SysUserResp();
            BeanUtils.copyProperties(user, resp);
            return resp;
        }).toList();
    }
}




