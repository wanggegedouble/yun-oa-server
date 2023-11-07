package com.wy.yunoa.SpringSecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

/**
 * @Author: wy
 * @CreateTime: 2023-11-08  01:51
 * @Description: TODO
 * @Version: 1.0
 */
@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(registry->{
            registry.requestMatchers("/admin/system/index/login").permitAll().anyRequest().authenticated();
        });
        http.formLogin(withDefaults());
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/admin/modeler/**","/diagram-viewer/**","/editor-app/**","/*.html",
                "/admin/processImage/**",
                "/admin/wechat/authorize","/admin/wechat/userInfo","/admin/wechat/bindPhone",
                "/favicon.ico","/swagger-resources/**", "/webjars/**", "/v3/**", "/swagger-ui.html/**", "/doc.html");
    }


}
