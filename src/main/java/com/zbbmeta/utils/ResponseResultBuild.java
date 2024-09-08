package com.zbbmeta.utils;

import com.zbbmeta.basic.IBasicEnum;
import com.zbbmeta.basic.ResponseResult;

import java.util.Date;

/**
 * @Author: springboot葵花宝典
 * @Github: https://github.com/bangbangzhou
 * @description: 构造ResponseWrap工具
 */
public class ResponseResultBuild {

    public static <T> ResponseResult<T> build(IBasicEnum basicEnumIntface, T t){

        //从UserVoContext中拿到userVoString
//        String userVoString = UserVoContext.getUserVoString();
//        UserVo userVo = null;
//        if (!EmptyUtil.isNullOrEmpty(userVoString)){
//            userVo = JSONObject.parseObject(userVoString, UserVo.class);
//        }else {
//            userVo = new UserVo();
//        }
        //构建对象
        return ResponseResult.<T>builder()
                .code(basicEnumIntface.getCode())
                .msg(basicEnumIntface.getMsg())
                .operationTime(new Date())
//                .userId(userVo.getId())
//                .userName(userVo.getUsername())
                .data(t)
                .build();
    }
}
