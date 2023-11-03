package com.wy.yunoa.model.VO;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Author: wy
 * @CreateTime: 2023-10-30  17:14
 * @Description: TODO
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
public class MetaVo {
    /**
     * 设置该路由在侧边栏和面包屑中展示的名字
     */
    private String title;

    /**
     * 设置该路由的图标，对应路径src/assets/icons/svg
     */
    private String icon;
}
