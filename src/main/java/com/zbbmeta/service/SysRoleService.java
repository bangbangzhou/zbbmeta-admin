package com.zbbmeta.service;

import com.zbbmeta.basic.entity.SysRole;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Set;

/**
* @author zbb
* @description 针对表【sys_role(角色信息表)】的数据库操作Service
* @createDate 2024-09-22 19:29:13
*/
public interface SysRoleService extends IService<SysRole> {

    Set<String> selectRolePermissionByUserId(Long userId);
}
