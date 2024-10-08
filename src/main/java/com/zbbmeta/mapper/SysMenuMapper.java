package com.zbbmeta.mapper;

import com.zbbmeta.basic.entity.SysMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
* @author zbb
* @description 针对表【sys_menu(菜单权限表)】的数据库操作Mapper
* @createDate 2024-09-22 19:42:14
* @Entity com.zbbmeta.basic.entity.SysMenu
*/
public interface SysMenuMapper extends BaseMapper<SysMenu> {

    List<String> selectMenuPermsByRoleId(Long roleId);

    List<String> selectMenuPermsByUserId(Long userId);
}




