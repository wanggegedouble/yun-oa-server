package com.wy.yunoa.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wy.yunoa.model.domain.SysMenu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author huawei
* @description 针对表【sys_menu(菜单表)】的数据库操作Mapper
* @createDate 2023-10-22 00:17:51
* @Entity generator.domain.SysMenu
*/
public interface SysMenuMapper extends BaseMapper<SysMenu> {

    List<SysMenu> findMenuByUserId(@Param("userId") Long userId);

    List<String> findPermsById(@Param("userId") Long userId);

}




