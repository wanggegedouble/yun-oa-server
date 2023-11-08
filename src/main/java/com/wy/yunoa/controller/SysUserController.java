package com.wy.yunoa.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wy.yunoa.Result.Result;
import com.wy.yunoa.model.DTO.SysUserQueryDTO;
import com.wy.yunoa.model.DTO.SysUserSaveDTO;
import com.wy.yunoa.model.VO.SysUserVO;
import com.wy.yunoa.service.SysUserService;
import io.swagger.v3.oas.annotations.Operation;
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
    @Operation(summary = "获取用户列表")
    private Result<List<SysUserVO>> selectList() {
        return Result.of("用户列表",userService.getList());
    }

    @GetMapping("/{page}/{limit}")
    @Operation(summary = "分页条件查询")
    public Result<Page<SysUserVO>> selectList(@PathVariable @Parameter(name = "pageNo",description = "当前页") Long page,
                                              @PathVariable @Parameter(name = "pageSize",description = "每页显示条数") Long limit,
                                              SysUserQueryDTO sysUserQueryDTO) {
        log.info("{} {}",page,limit);
        return Result.of("查询成功",this.userService.selectList(page,limit,sysUserQueryDTO));
    }

    @GetMapping("/test")
    public Result<String> selectVoList(SysUserQueryDTO sysUserQueryDTO) {
        log.info("sysUserQueryDTO~~~~~~~~~~~~~{}",sysUserQueryDTO);
        return Result.of(200,"aa");
    }

    @GetMapping("/get/{id}")
    @Operation(summary = "获取用户")
    public Result<SysUserVO> getUserById(@PathVariable Long id) {
        return Result.of("获取成功",this.userService.getUserById(id));
    }

    @DeleteMapping("remove/{id}")
    @Operation(summary = "删除用户")
    public Result delUserById(@PathVariable Long id) {
        this.userService.delUserById(id);
        return Result.of(200,"删除成功");
    }

    @GetMapping("updateStatus/{id}/{status}")
    @Operation(summary = "更新用户状态")
    public Result updateUserStatus(@PathVariable Long id,
                                   @PathVariable Integer status) {
        this.userService.updateStatus(id,status);
        return Result.of(200,"更新成功");
    }

    @PostMapping("save")
    @Operation(summary = "添加用户")
    public Result save(@RequestBody SysUserSaveDTO sysUserSaveDTO) {
        this.userService.saveUser(sysUserSaveDTO);
        return Result.of(200,"添加成功");
    }
}
