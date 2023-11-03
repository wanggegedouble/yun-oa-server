package com.wy.yunoa.service.impl;

import cn.hutool.core.util.IdUtil;
import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;
import cn.hutool.crypto.digest.MD5;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wy.yunoa.exception.CustomException;
import com.wy.yunoa.mapper.SysMenuMapper;
import com.wy.yunoa.model.DTO.SysUserQueryDTO;
import com.wy.yunoa.model.DTO.SysUserSaveDTO;
import com.wy.yunoa.model.VO.MetaVo;
import com.wy.yunoa.model.VO.RouterVO;
import com.wy.yunoa.model.VO.SysUserVO;
import com.wy.yunoa.model.domain.SysMenu;
import com.wy.yunoa.model.domain.SysUser;
import com.wy.yunoa.mapper.SysUserMapper;
import com.wy.yunoa.service.SysUserService;
import com.wy.yunoa.utils.MenuHelper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
* @author huawei
* @description 针对表【sys_user(用户表)】的数据库操作Service实现
* @createDate 2023-10-22 00:17:51
*/
@Service
@Slf4j
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Resource
    private SysUserMapper userMapper;
    @Resource
    private SysMenuMapper menuMapper;

    @Override
    public List<SysUserVO> getList() {
        List<SysUser> sysUsers = userMapper.selectList(null);
        if (Optional.ofNullable(sysUsers).isEmpty()) {
            throw new CustomException(400,"无数据");
        }
        return sysUsers.stream().map(user -> {
            SysUserVO resp = new SysUserVO();
            BeanUtils.copyProperties(user, resp);
            return resp;
        }).toList();
    }

    @Override
    public SysUser getUserInfoById(Long userId) {
        SysUser sysUser = userMapper.selectById(userId);
        if (Optional.ofNullable(sysUser).isEmpty()){
            throw new CustomException(400,"用户不存在");
        }
        return sysUser;
    }

    /*
    根据用户id查询用户菜单
     */
    @Override
    public List<RouterVO> findUserMenuById(Long userId) {
        // 判断用户是否是管理员 管理员直接查询说有的菜单 非管理员根据ID查询菜单
        List<SysMenu> menuList = null;
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

    @Override
    public List<String> findButtonsById(Long userId) {
        List<SysMenu> menuList;
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
    public Page<SysUserVO> selectList(Long page, Long limit, SysUserQueryDTO sysUserQueryDTO) {
        Page<SysUser> pageParam = new Page<>(page,limit);
        LambdaQueryWrapper<SysUser> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(StringUtils.isNotEmpty(sysUserQueryDTO.getKeyword()),SysUser::getUsername,sysUserQueryDTO.getKeyword());
        if (sysUserQueryDTO.getCreateTimeBegin() != null) {
            wrapper.ge(SysUser::getCreateTime,sysUserQueryDTO.getCreateTimeBegin());
        }
        if (sysUserQueryDTO.getCreateTimeEnd() != null) {
            wrapper.le(SysUser::getCreateTime,sysUserQueryDTO.getCreateTimeEnd());
        }
        Page<SysUser> sysUserPage = this.userMapper.selectPage(pageParam, wrapper);
        List<SysUser> records = sysUserPage.getRecords();
        if (Optional.ofNullable(records).isEmpty()) {
            return new Page<>();
        }
        List<SysUserVO> list = records.stream().map(user -> {
            SysUserVO userResp = new SysUserVO();
            BeanUtils.copyProperties(user, userResp);
            return userResp;
        }).toList();
        Page<SysUserVO> respPage = new Page<>();
        BeanUtils.copyProperties(sysUserPage,respPage);
        respPage.setRecords(list);
        log.info("total~~~~~~~~~~~~~~~:{}",sysUserPage.getTotal());
        return respPage;
    }

    /**
     *  根据Id获取用户信息
     */
    @Override
    public SysUserVO getUserById(Long id) {
        LambdaQueryWrapper<SysUser> wrapper = new LambdaQueryWrapper<>();
        SysUser sysUser = this.userMapper.selectById(id);
        wrapper.eq(SysUser::getStatus,true);
        if (Optional.ofNullable(sysUser).isEmpty()){
            throw new CustomException(400,"无此用户");
        }
        SysUserVO userVO = new SysUserVO();
        BeanUtils.copyProperties(sysUser,userVO);
        return userVO;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delUserById(Long id) {
        SysUser sysUser = this.userMapper.selectById(id);
        if (Optional.ofNullable(sysUser).isEmpty()) {
            throw new CustomException(400,"用户不存在");
        }
        int i = this.userMapper.deleteById(sysUser);
        if (i != 1) {
            throw new CustomException(500,"删除失败");
        }
    }

    @Override
    public void updateStatus(Long id, Integer status) {
        SysUser sysUser = this.userMapper.selectById(id);
        if (Optional.ofNullable(sysUser).isEmpty()) {
            throw new CustomException(400,"无此用户");
        }
        sysUser.setStatus(status);
        int i = this.userMapper.updateById(sysUser);
        if (i != 1) {
            throw new CustomException(500,"更新失败");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveUser(SysUserSaveDTO sysUserSaveDTO) {
        SysUser user = new SysUser();
        BeanUtils.copyProperties(sysUserSaveDTO,user);
        Digester md5 = new Digester(DigestAlgorithm.MD5);
        user.setPassword( md5.digestHex(user.getPassword()));
        int insert = this.userMapper.insert(user);
        if (insert != 1){
            throw new CustomException(500,"添加失败");
        }
    }

    // 构建路由
    private List<RouterVO> buildRouter(List<SysMenu> sysMenuTreeList) {
        // 提前创建,存储最终的数据
        List<RouterVO> routers = new ArrayList<>();
        for (SysMenu menu : sysMenuTreeList) {
            RouterVO router = new RouterVO();
            router.setHidden(false);
            router.setAlwaysShow(false);
            router.setPath(getRouterPath(menu));
            router.setComponent(menu.getComponent());
            router.setMeta(new MetaVo(menu.getName(),menu.getIcon()));
            //下层数据
            List<SysMenu> children = menu.getChildren();
            //type == 1 为菜单
            if (menu.getType() == 1) {
                List<SysMenu> hiddenMenuList = children.stream()
                        .filter(childrenMenu -> StringUtils.isNotEmpty(childrenMenu.getComponent()))
                        .toList();
                log.info("hiddenList:~~~{}",hiddenMenuList);
                for (SysMenu hiddenMenu : hiddenMenuList) {
                    RouterVO hiddenRouter = new RouterVO();
                    hiddenRouter.setHidden(true);
                    hiddenRouter.setAlwaysShow(false);
                    hiddenRouter.setPath(getRouterPath(hiddenMenu));
                    hiddenRouter.setComponent(hiddenMenu.getComponent());
                    hiddenRouter.setMeta(new MetaVo(hiddenMenu.getName(),hiddenMenu.getIcon()));
                    routers.add(hiddenRouter);
                }
            } else {
                if (!CollectionUtils.isEmpty(children)) {
                    if (children.size() > 0) {
                        router.setAlwaysShow(true);
                    }
                    router.setChildren(buildRouter(children));
                }
            }
            routers.add(router);
        }
        return routers;
    }

    /*
    获取路由路径
     */
    private String getRouterPath(SysMenu menu) {
        String routerPath = "/" + menu.getPath();
        if(menu.getParentId().intValue() != 0) {
            routerPath = menu.getPath();
        }
        return routerPath;
    }
}




