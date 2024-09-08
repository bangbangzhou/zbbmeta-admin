package com.zbbmeta.exception;

import com.zbbmeta.basic.IBasicEnum;

/**
 * @Author: springboot葵花宝典
 * @Github: https://github.com/bangbangzhou
 * @description: 自定义异常
 */
public class ProjectException extends RuntimeException {

    //错误编码
    private String code;

    //提示信息
    private String message;

    //异常接口
    private IBasicEnum basicEnum;

    public ProjectException() {
    }

    public ProjectException(IBasicEnum basicEnumIntface) {
        this.code = basicEnumIntface.getCode();
        this.message = basicEnumIntface.getMsg();
        this.basicEnum = basicEnumIntface;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public IBasicEnum getBasicEnum() {
        return basicEnum;
    }

    public void setBasicEnum(IBasicEnum basicEnum) {
        this.basicEnum = basicEnum;
    }

    @Override
    public String toString() {
        return "ProjectException{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}