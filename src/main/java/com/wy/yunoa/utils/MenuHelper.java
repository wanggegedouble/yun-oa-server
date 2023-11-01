package com.wy.yunoa.utils;

import com.wy.yunoa.model.domain.SysMenu;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @Author: wy
 * @CreateTime: 2023-10-30  21:00
 * @Description: TODO
 * @Version: 1.0
 */
public class MenuHelper {

    public static List<SysMenu> buildTree(List<SysMenu> menuList) {
        List<SysMenu> tress = new ArrayList<>();
        for (SysMenu menu : menuList) {
            // parentID = 0
            if (menu.getParentId().intValue() == 0) {
                tress.add(getChildMenu(menu,menuList));
            }
        }
        return tress;
    }

    private static SysMenu getChildMenu(SysMenu menu,List<SysMenu> menuList) {
        menu.setChildren(new ArrayList<>());
        for (SysMenu sysMenu : menuList) {
            if (Objects.equals(menu.getId(), sysMenu.getParentId())) {
                if (menu.getChildren() == null) {
                    menu.setChildren(new ArrayList<>());
                }
                menu.getChildren().add(getChildMenu(sysMenu,menuList));
            }
        }
        return menu;
    }
}
