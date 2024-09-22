package com.zbbmeta.exception;


import com.zbbmeta.enums.UserEnum;

/**
 * 用户不存在异常类
 *
 * @author ruoyi
 */
public class UserNotExistsException extends  UserException
{

    public UserNotExistsException(UserEnum userInvalid)
    {
        super(UserEnum.USER_INVALID);
    }



}
