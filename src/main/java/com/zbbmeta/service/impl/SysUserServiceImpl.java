package com.zbbmeta.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zbbmeta.basic.entity.SysUser;
import com.zbbmeta.service.SysUserService;
import com.zbbmeta.mapper.SysUserMapper;
import org.springframework.stereotype.Service;

/**
* @author zbb
* @description 针对表【sys_user(用户信息表)】的数据库操作Service实现
* @createDate 2024-09-08 18:44:09
*/
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser>
    implements SysUserService{

}




