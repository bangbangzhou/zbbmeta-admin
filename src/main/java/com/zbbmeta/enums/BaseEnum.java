package com.zbbmeta.enums;

import com.zbbmeta.basic.IBasicEnum;

/**
 * @Author: springboot葵花宝典
 * @Github: https://github.com/bangbangzhou
 * @description: TODO
 */
public enum BaseEnum implements IBasicEnum {

    SUCCEED("200","操作成功"),
    SYSYTEM_FAIL("111000","系统运行异常")
            ;

    private String code;
    private String msg;

    BaseEnum(String code, String msg) {
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

