package com.wy.yunoa.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wy.yunoa.exception.CustomException;
import com.wy.yunoa.model.VO.MetaVo;
import com.wy.yunoa.model.VO.RouterVO;
import com.wy.yunoa.model.VO.SysMenuVO;
import com.wy.yunoa.model.domain.SysMenu;
import com.wy.yunoa.mapper.SysMenuMapper;
import com.wy.yunoa.service.SysMenuService;
import com.wy.yunoa.utils.MenuHelper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
* @author huawei
* @description 针对表【sys_menu(菜单表)】的数据库操作Service实现
* @createDate 2023-10-22 00:17:51
*/
@Service
@Slf4j
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {
    @Resource
    private SysMenuMapper menuMapper;

    @Override
    public List<SysMenuVO> getMenuList() {
        List<SysMenu> sysMenus = menuMapper.selectList(null);
        if (Optional.ofNullable(sysMenus).isEmpty()) {
            throw new CustomException(400,"无数据");
        }
        List<SysMenu> menuTree = MenuHelper.buildTree(sysMenus);
        // 加载成前端需要的VO类
        List<SysMenuVO> menuVOList = new ArrayList<>();
        for (SysMenu menu : menuTree) {
            SysMenuVO sysMenuVO = this.convertNodeToVO(menu);
            menuVOList.add(sysMenuVO);
        }
        return menuVOList;
    }

    public SysMenuVO convertNodeToVO(SysMenu menu) {
        SysMenuVO sysMenuVO = new SysMenuVO();
        BeanUtils.copyProperties(menu,sysMenuVO);
        List<SysMenu> children = menu.getChildren();
        if (children != null && !children.isEmpty()) {
            List<SysMenuVO> childVOList = new ArrayList<>();
            for (SysMenu child : children) {
                SysMenuVO childVO = convertNodeToVO(child);
                childVOList.add(childVO);
            }
            sysMenuVO.setChildren(childVOList);
        }
        return sysMenuVO;
    }

    @Override
    public List<RouterVO> findUserMenuById(Long userId) {
        // 判断用户是否是管理员 管理员直接查询说有的菜单 非管理员根据ID查询菜单
        List<SysMenu> menuList;
        if (userId.intValue() == 1) {
            menuList = menuMapper.selectList(Wrappers.<SysMenu>lambdaQuery()
                    .eq(SysMenu::getStatus, 1)
                    .orderByAsc(SysMenu::getSortValue));
        } else {
            menuList = menuMapper.findMenuByUserId(userId);
        }
        //2 把查询出来数据列表-构建成框架要求的路由结构 使用菜单操作工具类构建树形结构
        List<SysMenu> sysMenuTreeList = MenuHelper.buildTree(menuList);
        return this.buildRouter(sysMenuTreeList);
    }

    private List<RouterVO> buildRouter(List<SysMenu> sysMenuTreeList) {
        // 提前创建,存储最终的数据
        List<RouterVO> routers = new ArrayList<>();
        for (SysMenu menu : sysMenuTreeList) {
            RouterVO router = new RouterVO();
            router.setHidden(false);
            router.setAlwaysShow(true);
            router.setPath(getRouterPath(menu));
            router.setComponent(menu.getComponent());
            router.setMeta(new MetaVo(menu.getName(), menu.getIcon()));
            //下层数据
            List<SysMenu> children = menu.getChildren();
            //type == 1 为菜单
            if (menu.getType() == 1) {
                //获取type == 1 需要隐藏的子路由
                List<SysMenu> hiddenMenuList = children.stream()
                        .filter(childrenMenu -> StringUtils.isNotEmpty(childrenMenu.getComponent()))
                        .toList();
                log.info("hiddenList:~~~{}", hiddenMenuList);
                for (SysMenu hiddenMenu : hiddenMenuList) {
                    RouterVO hiddenRouter = new RouterVO();
                    hiddenRouter.setHidden(true);
                    hiddenRouter.setAlwaysShow(false);
                    hiddenRouter.setPath(getRouterPath(hiddenMenu));
                    hiddenRouter.setComponent(hiddenMenu.getComponent());
                    hiddenRouter.setMeta(new MetaVo(hiddenMenu.getName(), hiddenMenu.getIcon()));
                    routers.add(hiddenRouter);
                }
            } else {
                // type !== 1
                if (!CollectionUtils.isEmpty(children)) {
                    if (children.size() > 0) {
                        router.setAlwaysShow(true);
                    }
                    router.setChildren(buildRouter(children));
                }
            }
            routers.add(router);
        }
        log.info("routers!!!!!{}",routers);
        return routers;
    }

    @Override
    public List<String> findButtonsById(Long userId) {
        List<SysMenu> menuList = null;
        // 如果是admin 用户直接查询
        if (userId.intValue() == 1) {
            menuList = this.menuMapper.selectList(Wrappers.<SysMenu>lambdaQuery().eq(SysMenu::getStatus,1));
        } else {
            //按照userID查询
            menuList = this.menuMapper.findMenuByUserId(userId);
        }
        return menuList.stream()
                .filter(menu->menu.getType() == 2)
                .map(SysMenu::getPerms)
                .toList();
    }

    @Override
    public List<SysMenu> find() {

        List<SysMenu> sysMenus = this.menuMapper.selectList(Wrappers.<SysMenu>lambdaQuery().eq(SysMenu::getStatus, 1));
        return MenuHelper.buildTree(sysMenus);
    }

    private String getRouterPath(SysMenu menu) {
        String routerPath = "/" + menu.getPath();
        if(menu.getParentId().intValue() != 0) {
            routerPath = menu.getPath();
        }
        return routerPath;
    }
}






