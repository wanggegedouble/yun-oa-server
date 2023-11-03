package com.wy.yunoa.controller;

import com.wy.yunoa.Result.Result;
import com.wy.yunoa.model.VO.SysMenuVO;
import com.wy.yunoa.service.SysMenuService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: wy
 * @CreateTime: 2023-10-22  16:31
 * @Description: TODO
 * @Version: 1.0
 */
@RestController
@RequestMapping("/sys/menu")
@Tag(name = "菜单管理")
public class SysMenuController {
    @Resource
    private SysMenuService menuService;

    @GetMapping("/list")
    @Tag(name = "获取菜单列表")
    public Result<List<SysMenuVO>> getList() {
        return Result.of("获取菜单列表成功",menuService.getList());
    }
}
