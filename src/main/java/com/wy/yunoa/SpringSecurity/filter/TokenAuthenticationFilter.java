package com.wy.yunoa.SpringSecurity.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * @Author: wy
 * @CreateTime: 2023-11-08  05:56
 * @Description: TODO
 * @Version: 1.0
 */

public class TokenAuthenticationFilter extends OncePerRequestFilter {
    private RedisTemplate<String,Object> redisTemplate;

    public TokenAuthenticationFilter(RedisTemplate<String,Object> redisTemplate) {
     this.redisTemplate = redisTemplate;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        if ("/admin/system/index/login".contentEquals(request.getRequestURL())) {
            filterChain.doFilter(request,response);
        }
        UsernamePasswordAuthenticationToken authenticationToken = getAuthentication(request);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {

        return null;
    }
}
