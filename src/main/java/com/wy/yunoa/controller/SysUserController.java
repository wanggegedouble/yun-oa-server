package com.wy.yunoa.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wy.yunoa.Result.Result;
import com.wy.yunoa.model.DTO.SysUserQueryDTO;
import com.wy.yunoa.model.Resp.SysUserResp;
import com.wy.yunoa.service.SysUserService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: wy
 * @CreateTime: 2023-10-22  00:28
 * @Description: TODO
 * @Version: 1.0
 */
@RestController
@RequestMapping("/admin/system/sysUser")
@Slf4j
@Tag(name = "用户管理")
public class SysUserController {

    @Resource
    private SysUserService userService;

    @GetMapping("/userList")
    @Tag(name = "获取用户列表")
    @Deprecated
    private Result<List<SysUserResp>> selectList() {
        return Result.of("用户列表",userService.getList());
    }

    @GetMapping("/{page}/{limit}")
    @Tag(name = "分页查询")
    public Result<Page<SysUserResp>> selectList(@PathVariable @Parameter(name = "page",description = "当前页") Long page,
                                                @PathVariable @Parameter(name = "limit",description = "每页显示条数") Long limit,
                                                SysUserQueryDTO sysUserQueryDTO) {
        log.info("{} {}",page,limit);
        return Result.of("查询成功",this.userService.selectList(page,limit,sysUserQueryDTO));
    }

}
