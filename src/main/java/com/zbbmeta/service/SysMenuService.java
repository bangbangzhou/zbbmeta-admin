package com.zbbmeta.service;

import com.zbbmeta.basic.entity.SysMenu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Set;

/**
* @author zbb
* @description 针对表【sys_menu(菜单权限表)】的数据库操作Service
* @createDate 2024-09-22 19:42:14
*/
public interface SysMenuService extends IService<SysMenu> {

     Set<String> selectMenuPermsByRoleId(Long roleId);

     Set<String> selectMenuPermsByUserId(Long userId);
}
