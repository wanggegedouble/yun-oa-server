package com.wy.yunoa.service.impl;

import com.wy.yunoa.model.Resp.RouterResp;
import com.wy.yunoa.service.SysUserService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class SysUserServiceImplTest {

    @Resource
    private SysUserService userService;

    @Test
    void findUserMenuById() {
    }
}