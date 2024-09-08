package com.zbbmeta.advice;


import com.alibaba.fastjson.JSON;
import com.zbbmeta.basic.ResponseResult;
import com.zbbmeta.enums.BaseEnum;
import com.zbbmeta.exception.ProjectException;
import com.zbbmeta.utils.ExceptionsUtil;
import com.zbbmeta.utils.ResponseResultBuild;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: springboot葵花宝典
 * @Github: https://github.com/bangbangzhou
 * @description: 基础的controller,对controller层的增强，其他的controller则不需要继承，也会被拦截处理
 */
@ControllerAdvice
@Slf4j
public class BaseControllerAdvice {
    //表示当请求发生异常时，被ExceptionHandler注释的方法会去处理
    @ExceptionHandler
    public void ExceptionHandler(Exception ex, HttpServletResponse response) throws IOException {
        ResponseResult<Object> responseWrap = null;
        //自定义异常
        if (ex instanceof ProjectException){
            ProjectException projectException = (ProjectException) ex;
            responseWrap = ResponseResultBuild.build(projectException.getBasicEnum(), null);
        }else {
            //系统异常
            responseWrap = ResponseResultBuild.build(BaseEnum.SYSYTEM_FAIL, null);
            log.error("系统异常：{}", ExceptionsUtil.getStackTraceAsString(ex));
        }
        //编码防止中文问题
        response.setContentType("application/json;charset =utf-8");
        response.getWriter().write(JSON.toJSONString(responseWrap));
    }
}
