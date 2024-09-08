package com.zbbmeta.basic;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: springboot葵花宝典
 * @Github: https://github.com/bangbangzhou
 * @description: 统一返回数据
 */


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class  ResponseResult<T> implements Serializable {

    //响应返回编码
    private String code;

    //响应返回信息
    private String msg;

    //返回结果
    private T data;

    //操作用户
    private Long userId;

    //操作用户名称
    private String userName;

    //创建时间,处理json的时间参数解析
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss",timezone = "GMT+8")
    private Date operationTime;
}
