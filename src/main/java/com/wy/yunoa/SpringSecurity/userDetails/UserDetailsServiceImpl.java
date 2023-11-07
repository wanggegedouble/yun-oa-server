package com.wy.yunoa.SpringSecurity.userDetails;

import com.wy.yunoa.model.domain.SysUser;
import com.wy.yunoa.service.SysMenuService;
import com.wy.yunoa.service.SysUserService;
import jakarta.annotation.Resource;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @Author: wy
 * @CreateTime: 2023-11-08  03:44
 * @Description: TODO
 * @Version: 1.0
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Resource
    private SysUserService userService;
    @Resource
    SysMenuService menuService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser sysUser = this.userService.findUserByUsername(username);
        if (Optional.ofNullable(sysUser).isEmpty()) {
            throw new UsernameNotFoundException("无此用户");
        }
        if (sysUser.getStatus() == 0) {
            throw new RuntimeException("账号已停用");
        }
        List<String> buttonsById = this.menuService.findButtonsById(sysUser.getId());
        List<SimpleGrantedAuthority> authorityList = buttonsById.stream().map(item -> new SimpleGrantedAuthority(item.trim())).toList();
        return new CustomUser(sysUser,authorityList);
    }
}
