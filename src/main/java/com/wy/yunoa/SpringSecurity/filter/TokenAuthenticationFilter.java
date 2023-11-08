package com.wy.yunoa.SpringSecurity.filter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wy.yunoa.Result.ResponseResult;
import com.wy.yunoa.Result.Result;
import com.wy.yunoa.Result.ResultCodeEnum;
import com.wy.yunoa.utils.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.*;

/**
 * @Author: wy
 * @CreateTime: 2023-11-08  05:56
 * @Description: TODO
 * @Version: 1.0
 */

@Slf4j
public class TokenAuthenticationFilter extends OncePerRequestFilter {

    private final RedisTemplate<String,Object> redisTemplate;

    public TokenAuthenticationFilter(RedisTemplate<String,Object> redisTemplate) {
     this.redisTemplate = redisTemplate;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        log.info("URL~~~~~~~~{}",request.getRequestURI());
        if ("/admin/system/index/login".equals(request.getRequestURI())) {
            log.info("登录接口~~~~~~~~~~~~~~~~~~~");
            filterChain.doFilter(request,response);
        }
        UsernamePasswordAuthenticationToken authenticationToken = null;
        try {
             authenticationToken = getAuthentication(request);
             if (Optional.ofNullable(authenticationToken).isEmpty()) {
                 throw new RuntimeException("认证失败");
             }
        } catch (JsonProcessingException e) {
            ResponseResult.of(response,Result.of(ResultCodeEnum.JACKSON_ERROR,null));
        } catch (Exception e) {
            ResponseResult.of(response,Result.of(ResultCodeEnum.NO_AUTHENTICATION,null));
        }
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        filterChain.doFilter(request,response);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) throws JsonProcessingException {
        String token = request.getHeader("token");
        if (StringUtils.isNotEmpty(token)) {
            String username = JwtUtil.getUsername(token);
            if (StringUtils.isNotEmpty(username)) {
                String permsList = (String)redisTemplate.opsForValue().get(username);
                if (StringUtils.isNotEmpty(permsList)) {
                    ObjectMapper objectMapper = new ObjectMapper();
                    List<Map<String, Object>> maps = objectMapper.readValue(permsList, new TypeReference<>() {});
                    log.info("maps~~~~~~~~~~~{}",maps);
                    List<SimpleGrantedAuthority> authorityList = maps.stream().map(item -> {
                        String authority = (String) item.get("authority");
                        return new SimpleGrantedAuthority(authority);
                    }).toList();
                    return new UsernamePasswordAuthenticationToken(username,null,authorityList);
                } else {
                    return new UsernamePasswordAuthenticationToken(username,null, Collections.emptyList());
                }
            }
        }
        return null;
    }
}
