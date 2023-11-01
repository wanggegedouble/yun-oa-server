package com.wy.yunoa.controller;

import com.wy.yunoa.Result.Result;
import com.wy.yunoa.model.DTO.IndexLoginDTO;
import com.wy.yunoa.model.Resp.RouterResp;
import com.wy.yunoa.model.domain.SysUser;
import com.wy.yunoa.service.SysLoginService;
import com.wy.yunoa.service.SysUserService;
import com.wy.yunoa.utils.JWT.JWTHpler;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: wy
 * @CreateTime: 2023-10-26  00:19
 * @Description: TODO
 * @Version: 1.0
 */
@RestController
@RequestMapping("/admin/system/index")
@Slf4j
public class SysIndexController {

    @Resource
    private SysLoginService loginService;
    @Resource
    private SysUserService userService;

    @GetMapping("/getRouters")
    public String getRouters(){
        return "getRouters";
    }

    @PostMapping("/login")
    public Result login(@RequestBody IndexLoginDTO loginDTO){
        String token = this.loginService.login(loginDTO);
        log.info(token);
        return Result.of("登录信息", Map.of("token",token));
    }

    @GetMapping("/info")
    public Result getInfo(HttpServletRequest request){
        String token = request.getHeader("token");
        Long userId = JWTHpler.getUserId(token);
        SysUser user = this.userService.getUserInfoById(userId);
        //根据用户id查询用户菜单
        List<RouterResp> routers = this.userService.findUserMenuById(userId);
        //根据用户ID获取用户可以操作的按钮
        List<String> permsList = this.userService.findButtonsById(userId);
        Map<String,Object> map = new HashMap<>();
        map.put("roles","[admin]");
        map.put("name",user.getUsername());
        map.put("avatar","https://oss.aliyuncs.com/aliyun_id_photo_bucket/default_handsome.jpg");
        map.put("routers",routers);
        map.put("buttons",permsList);
        return Result.of("info",map);
    }

    @PostMapping("/logout")
    public Result logout() {
        return Result.of(200,"logout");
    }
}
