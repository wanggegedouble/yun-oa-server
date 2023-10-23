package com.wy.yunoa.controller;

import com.wy.yunoa.Result.Result;
import com.wy.yunoa.model.Resp.SysUserResp;
import com.wy.yunoa.service.SysUserService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: wy
 * @CreateTime: 2023-10-22  00:28
 * @Description: TODO
 * @Version: 1.0
 */
@RestController
@RequestMapping("/sys/user")
public class SysUserController {

    @Resource
    private SysUserService userService;

    @GetMapping("/userList")
    private Result<List<SysUserResp>> selectList() {
        return Result.of("用户列表",userService.getList());
    }

}
