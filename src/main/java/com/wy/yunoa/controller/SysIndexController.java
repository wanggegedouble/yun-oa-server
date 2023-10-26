package com.wy.yunoa.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: wy
 * @CreateTime: 2023-10-26  00:19
 * @Description: TODO
 * @Version: 1.0
 */
@RestController
@RequestMapping("/sys/index")
public class SysIndexController {

    @GetMapping("/getRouters")
    public String getRouters(){
        return "getRouters";
    }
}
