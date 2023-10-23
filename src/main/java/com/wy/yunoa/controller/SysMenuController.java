package com.wy.yunoa.controller;

import com.wy.yunoa.Result.Result;
import com.wy.yunoa.model.Resp.SysMenuResp;
import com.wy.yunoa.service.SysMenuService;
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
public class SysMenuController {
    @Resource
    private SysMenuService menuService;

    @GetMapping("/list")
    public Result<List<SysMenuResp>> getList() {
        return Result.of("获取菜单列表成功",menuService.getList());
    }
}
