package com.wy.yunoa.model.Resp;

import lombok.Data;

import java.util.List;

/**
 * @Author: wy
 * @CreateTime: 2023-10-30  17:13
 * @Description: TODO
 * @Version: 1.0
 */
@Data
public class RouterResp {
    /**
     * 路由地址
     */
    private String path;

    /**
     * 是否隐藏路由，当设置 true 的时候该路由不会再侧边栏出现
     */
    private boolean hidden;

    /**
     * 组件地址
     */
    private String component;

    /**
     * 当你一个路由下面的 children 声明的路由大于1个时，自动会变成嵌套的模式--如组件页面
     */
    private Boolean alwaysShow;

    /**
     * 其他元素
     */
    private MetaVo meta;

    /**
     * 子路由
     */
    private List<RouterResp> children;
}
