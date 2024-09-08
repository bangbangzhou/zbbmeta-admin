package com.zbbmeta.enums;

import com.zbbmeta.basic.IBasicEnum;

/**
 * @Author: springboot葵花宝典
 * @Github: https://github.com/bangbangzhou
 * @description: TODO
 */
public enum UserEnum implements IBasicEnum {
    SUCCEED("200","操作成功"),
    LOGOUT_SUCCEED("1004","退出成功"),
    FAIL("1000","操作失败"),
    /**
     * 认证错误，不知道啥原因
     */
    OAUTH2_EXCEPTION("400004", "认证错误!"),


    /**
     * access_token 不存在
     */
    ACCESS_TOKEN_INVALID("400010", "access_token 不存在"),


    /**
     * access_token 过期
     */
    ACCESS_TOKEN_EXPIRED("400011", "access_token 已过期"),


    /**
     * token 格式错误
     */
    TOKEN_FORMAT_ERROR("400012", "access_token 格式错误"),


    /**
     * 用户不存在
     */
    USER_INVALID("400020", "用户或者密码错误"),

    USER_ISDEL("400030", "用户已经删除"),

    USER_ISSTOP("400030", "用户已经停用"),
    /**
     * 当前用户被锁定
     */
    USER_LOCKING("400023", "此用户被锁定"),

    /**
     * 用户手机号不存在
     */
    USER_MOBILE_INVALID("400024", "用户手机号不存在"),

    /**
     * 授权类型错误
     */
    AUTHORIZATION_GRANT_TYPE("400033", "授权类型错误"),

    /**
     * 请求参数解析错误
     */
    REQUEST_PARAM_VALIDATE("400044", "请求参数解析错误"),

    /**
     * 验证码错误
     */
    VERIFICATION_CODE_ERROR("400045", "验证码错误"),


    PAGE_FAIL("70005", "查询用户列表失败"),
    CREATE_FAIL("70007", "保存用户失败"),
    UPDATE_FAIL("70008", "修改用户失败"),
    DELETE_FAIL("70009", "修改用户失败"),
    SELECT_USER_FAIL("70010", "查询用户失败"),
    SELECT_ROLE_FAIL("70011", "查询用户对应角色失败"),
    SELECT_RESOURCE_FAIL("70012", "查询用户对应资源失败"),
    SELECT_CURRENT_USER("70013", "查询当前用户失败"),
    SELECT_USER_LIST_FAIL("70014", "查询用户list失败"),
            ;

    private String code;
    private String msg;

    UserEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public String getCode() {
        return code;
    }
    @Override
    public String getMsg() {
        return msg;
    }

}

