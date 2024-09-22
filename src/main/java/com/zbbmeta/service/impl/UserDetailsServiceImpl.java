package com.zbbmeta.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.zbbmeta.basic.LoginUser;
import com.zbbmeta.basic.entity.SysUser;
import com.zbbmeta.enums.UserEnum;
import com.zbbmeta.exception.UserNotExistsException;
import com.zbbmeta.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @Author: springboot葵花宝典
 * @Github: https://github.com/bangbangzhou
 * @description: 用户验证处理
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysPermissionService sysPermissionService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser user = sysUserMapper.selectUserByUserName(username);
        if (BeanUtil.isEmpty(user))
        {

            throw new UserNotExistsException(UserEnum.USER_INVALID);
        }
        else if ("2".equals( user.getDelFlag()))
        {
            throw new UserNotExistsException(UserEnum.USER_ISDEL);

        }
        else if ("1".equals(user.getStatus()))
        {

            throw new UserNotExistsException(UserEnum.USER_ISSTOP);
        }
        return createLoginUser(user);
    }

    public UserDetails createLoginUser(SysUser user)
    {
        return new LoginUser(user.getUserId(), user.getDeptId(), user, sysPermissionService.getMenuPermission(user));
    }
}
