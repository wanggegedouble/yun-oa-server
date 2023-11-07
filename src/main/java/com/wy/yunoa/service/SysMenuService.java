package com.wy.yunoa.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wy.yunoa.Result.Result;
import com.wy.yunoa.model.VO.RouterVO;
import com.wy.yunoa.model.VO.SysMenuVO;
import com.wy.yunoa.model.domain.SysMenu;

import java.util.List;

/**
* @author huawei
* @description 针对表【sys_menu(菜单表)】的数据库操作Service
* @createDate 2023-10-22 00:17:51
*/
public interface SysMenuService {

    List<SysMenuVO> getMenuList();

    List<RouterVO> findUserMenuById(Long userId);

    List<String> findButtonsById(Long userId);

    List<SysMenu> find();
}
