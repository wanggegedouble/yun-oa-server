package com.wy.yunoa.SpringSecurity.filter;

import com.wy.yunoa.SpringSecurity.userDetails.CustomUser;
import com.wy.yunoa.model.domain.SysUser;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;

/**
 * @Author: wy
 * @CreateTime: 2023-11-09  15:46
 * @Description: TODO
 * @Version: 1.0
 */
@Service
public class TokenLogoutFilterImpl implements LogoutHandler {

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        CustomUser customUser = (CustomUser)authentication.getDetails();
        SysUser sysUser = customUser.getSysUser();
    }
}
