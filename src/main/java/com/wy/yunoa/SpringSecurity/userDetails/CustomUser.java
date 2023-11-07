package com.wy.yunoa.SpringSecurity.userDetails;

import com.wy.yunoa.model.domain.SysUser;
import lombok.Data;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.io.Serial;
import java.util.Collection;

/**
 * @Author: wy
 * @CreateTime: 2023-11-08  03:56
 * @Description: TODO
 * @Version: 1.0
 */
@Getter
public class CustomUser extends User {
    @Serial
    private static final long serialVersionUID = 4846461845266088807L;

    private final SysUser sysUser;

    public CustomUser(SysUser sysUser, Collection<? extends GrantedAuthority> authorities) {
        super(sysUser.getUsername(),sysUser.getPassword(),authorities);
        this.sysUser = sysUser;
    }

}
