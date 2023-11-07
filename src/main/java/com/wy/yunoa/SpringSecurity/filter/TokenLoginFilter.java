package com.wy.yunoa.SpringSecurity.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wy.yunoa.Result.Result;
import com.wy.yunoa.Result.ResultCodeEnum;
import com.wy.yunoa.SpringSecurity.userDetails.CustomUser;
import com.wy.yunoa.model.DTO.IndexLoginDTO;
import com.wy.yunoa.utils.JWT.JWTHpler;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.io.IOException;
import java.net.http.HttpRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: wy
 * @CreateTime: 2023-11-08  04:25
 * @Description: TODO
 * @Version: 1.0
 */
public class TokenLoginFilter extends UsernamePasswordAuthenticationFilter {

    private  RedisTemplate<String,Object> redisTemplate;

    public TokenLoginFilter(AuthenticationManager authenticationManager, RedisTemplate<String,Object> redisTemplate) {
        this.setAuthenticationManager(authenticationManager);
        this.setPostOnly(false);
        this.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/admin/system/index/login","POST"));
        this.redisTemplate = redisTemplate;
    }

    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
        try {
            IndexLoginDTO loginVO = new ObjectMapper().readValue(request.getInputStream(), IndexLoginDTO.class);
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginVO.getUsername(),loginVO.getPassword());
            return this.getAuthenticationManager().authenticate(authenticationToken);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        CustomUser customUser = (CustomUser)authResult.getPrincipal();
        String token = JWTHpler.createToken(customUser.getSysUser().getId(), customUser.getSysUser().getUsername());
        ObjectMapper objectMapper = new ObjectMapper();
        String auth = objectMapper.writeValueAsString(customUser.getAuthorities());
        redisTemplate.opsForValue().set(customUser.getSysUser().getUsername(),auth);
        Map<String,Object> map = new HashMap<>();
        map.put("token",token);
        response.setStatus(HttpStatus.OK.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        objectMapper.writeValue(response.getWriter(),Result.of("token",map));
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request,
                                              HttpServletResponse response,
                                              AuthenticationException failed) throws IOException, ServletException {
        response.setStatus(HttpStatus.OK.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        ObjectMapper objectMapper = new ObjectMapper();
        String message = failed.getMessage();
        objectMapper.writeValue(response.getWriter(),Result.of(ResultCodeEnum.LOG_ERROR,message));
    }
}
