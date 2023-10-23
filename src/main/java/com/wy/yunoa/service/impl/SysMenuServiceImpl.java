package com.wy.yunoa.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wy.yunoa.model.Resp.SysMenuResp;
import com.wy.yunoa.model.domain.SysMenu;
import com.wy.yunoa.mapper.SysMenuMapper;
import com.wy.yunoa.service.SysMenuService;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
* @author huawei
* @description 针对表【sys_menu(菜单表)】的数据库操作Service实现
* @createDate 2023-10-22 00:17:51
*/
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {
    @Resource
    private SysMenuMapper menuMapper;
    @Override
    public List<SysMenuResp> getList() {
        List<SysMenu> sysMenus = menuMapper.selectList(null);
        if (Optional.ofNullable(sysMenus).isEmpty()) {

        }
        return sysMenus.stream().map(menu -> {
            SysMenuResp menuResp = new SysMenuResp();
            BeanUtils.copyProperties(menu, menuResp);
            return menuResp;
        }).toList();
    }
}




