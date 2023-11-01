package com.wy.yunoa.service.impl;

import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.wy.yunoa.exception.CustomException;
import com.wy.yunoa.mapper.SysUserMapper;
import com.wy.yunoa.model.DTO.IndexLoginDTO;
import com.wy.yunoa.model.domain.SysUser;
import com.wy.yunoa.service.SysLoginService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.Serial;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: wy
 * @CreateTime: 2023-10-28  20:38
 * @Description: TODO
 * @Version: 1.0
 */
@Service
public class SysLoginServiceImpl implements SysLoginService {
    @Resource
    private SysUserMapper userMapper;
    @Value("${jwt.key}")
    private String key;
    @Value("${jwt.expire_time}")
    private Long expire_time;
    @Override
    public String login(IndexLoginDTO loginDTO) {
        LambdaQueryWrapper<SysUser> lambdaQuery = Wrappers.lambdaQuery();
        lambdaQuery.eq(SysUser::getUsername,loginDTO.getUsername());
        SysUser sysUser = userMapper.selectOne(lambdaQuery);
        if(sysUser == null){
            throw new CustomException(400,"用户不存在");
        }
        //获取数据库密码
        String password_db = sysUser.getPassword();
        //获取输入密码
        String password_input = loginDTO.getPassword();
        //比较密码
        Digester md5 = new Digester(DigestAlgorithm.MD5);
        String password_md5 = md5.digestHex(password_input);
        if (!password_md5.equals(password_db)){
            throw new CustomException(400,"密码错误");
        }
        Map<String,Object> map = new HashMap<>() {
            @Serial
            private static final long serialVersionUID = 161078396661764878L;
            {
                put("username",sysUser.getUsername());
                put("userId",sysUser.getId());
                put("expire_time",System.currentTimeMillis() + expire_time);
            }
        };
        return JWTUtil.createToken(map, key.getBytes());
    }
}
