package com.zbbmeta.mapper;

import com.zbbmeta.basic.entity.SysUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author zbb
* @description 针对表【sys_user(用户信息表)】的数据库操作Mapper
* @createDate 2024-09-08 21:15:22
* @Entity com.zbbmeta.basic.entity.SysUser
*/
public interface SysUserMapper extends BaseMapper<SysUser> {

    SysUser selectUserByUserName(String userName);


     SysUser selectUserById(Long userId);
}




