package com.zbbmeta.mapper;

import com.zbbmeta.basic.entity.SysRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
* @author zbb
* @description 针对表【sys_role(角色信息表)】的数据库操作Mapper
* @createDate 2024-09-22 19:29:13
* @Entity com.zbbmeta.basic.entity.SysRole
*/
public interface SysRoleMapper extends BaseMapper<SysRole> {
     List<SysRole> selectRolePermissionByUserId(Long userId);
}




