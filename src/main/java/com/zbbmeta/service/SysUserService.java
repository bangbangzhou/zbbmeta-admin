package com.zbbmeta.service;

import com.zbbmeta.basic.entity.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author zbb
* @description 针对表【sys_user(用户信息表)】的数据库操作Service
* @createDate 2024-09-08 21:15:22
*/
public interface SysUserService extends IService<SysUser> {

    SysUser selectUserById(Long userId);

}
