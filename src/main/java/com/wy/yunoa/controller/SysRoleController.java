package com.wy.yunoa.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wy.yunoa.Result.Result;
import com.wy.yunoa.model.DTO.SysAssginRoleVO;
import com.wy.yunoa.model.DTO.SysRolePageDTO;
import com.wy.yunoa.model.DTO.SysRoleSaveDTO;
import com.wy.yunoa.model.VO.SysRolePageVO;
import com.wy.yunoa.model.VO.SysRoleVO;
import com.wy.yunoa.service.SysRoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @Author:
 * @CreateTime: 2023-10-23  20:39
 * @Description: TODO
 * @Version: 1.0
 */
@RestController
@RequestMapping("/admin/system/sysRole")
@Tag(name = "角色管理")
public class SysRoleController {

    @Resource
    private SysRoleService roleService;

    @GetMapping("/list")
    @Operation(summary = "查询角色列表")
    public Result<List<SysRoleVO>> selectList() {
        return Result.of("查询成功",roleService.selectList());
    }

    @GetMapping("/{page}/{limit}")
    @Operation(summary = "条件分页查询")
    public Result<Page<SysRolePageVO>> selectPageList(@PathVariable(required = false) Long page,
                                                      @PathVariable(required = false) Long limit,
                                                      SysRolePageDTO sysRolePageDTO) {
        return Result.of("查询成功",this.roleService.selectPageList(page,limit,sysRolePageDTO));
    }

    @PostMapping("save")
    @Operation(summary = "添加角色")
    public Result saveRole(@RequestBody SysRoleSaveDTO sysRoleSaveDTO) {
        this.roleService.saveRole(sysRoleSaveDTO);
        return Result.of("200","添加成功");
    }

    @Operation(summary = "为用户分配角色")
    @PostMapping("/doAssign")
    public Result doAssign(@RequestBody SysAssginRoleVO sysAssginRoleVO) {
        this.roleService.doAssign(sysAssginRoleVO);
        return Result.of(200,"分配成功");
    }

    @Operation(summary = "获取角色")
    @GetMapping("/toAssign/{userId}")
    public Result<Map<String,Object>> toASSign(@PathVariable Long userId) {
        return Result.of("获取角色成功",this.roleService.toAssign(userId));
    }

}
