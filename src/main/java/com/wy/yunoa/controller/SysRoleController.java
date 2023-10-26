package com.wy.yunoa.controller;

import com.wy.yunoa.Result.Result;
import com.wy.yunoa.model.Resp.SysRoleResp;
import com.wy.yunoa.service.SysRoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author:
 * @CreateTime: 2023-10-23  20:39
 * @Description: TODO
 * @Version: 1.0
 */
@RestController
@RequestMapping("/sys/role")
@Tag(name = "角色管理")
public class SysRoleController {

    @Resource
    private SysRoleService roleService;

    @GetMapping("/list")
    @Operation(summary = "查询角色列表")
    public Result<List<SysRoleResp>> selectList() {
        return Result.of("查询成功",roleService.selectList());
    }
}
