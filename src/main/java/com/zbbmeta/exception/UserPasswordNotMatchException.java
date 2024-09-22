package com.zbbmeta.exception;

import com.zbbmeta.enums.UserEnum;

/**
 * 用户密码不正确或不符合规范异常类
 * 
 * @author ruoyi
 */
public class UserPasswordNotMatchException extends ProjectException
{
    private static final long serialVersionUID = 1L;

    public UserPasswordNotMatchException()
    {
        super(UserEnum.USER_INVALID);
    }
}
