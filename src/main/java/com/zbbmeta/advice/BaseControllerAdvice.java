package com.zbbmeta.advice;


import cn.hutool.core.text.StrPool;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.zbbmeta.basic.ResponseResult;
import com.zbbmeta.enums.BaseEnum;
import com.zbbmeta.enums.UserEnum;
import com.zbbmeta.exception.ProjectException;
import com.zbbmeta.utils.ExceptionsUtil;
import com.zbbmeta.utils.ResponseResultBuild;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.io.IOException;
import java.util.List;
import java.util.Set;

/**
 * @Author: springboot葵花宝典
 * @Github: https://github.com/bangbangzhou
 * @description: 基础的controller,对controller层的增强，其他的controller则不需要继承，也会被拦截处理
 */
@ControllerAdvice(annotations = {RestController.class, Controller.class})
@ResponseBody
@Slf4j
public class BaseControllerAdvice {
    //表示当请求发生异常时，被ExceptionHandler注释的方法会去处理
    @ExceptionHandler(Exception.class)
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

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public void httpMessageNotReadableException(HttpMessageNotReadableException ex, HttpServletRequest request, HttpServletResponse response) throws IOException {
        log.warn("HttpMessageNotReadableException:", ex);
        String msg = StrUtil.EMPTY;
        String message = ex.getMessage();
        ResponseResult<Object> responseWrap = null;
//        if (StrUtil.containsAny(message, "Could not read document:")) {
            msg = String.format("无法正确的解析json类型的参数：%s", StrUtil.subBetween(message, "Could not read document:", " at "));

            responseWrap = ResponseResultBuild.build(UserEnum.PARAM_EX, msg);

//        }

        //编码防止中文问题
        response.setContentType("application/json;charset =utf-8");
        response.getWriter().write(JSON.toJSONString(responseWrap));
    }

    /**
     * spring 封装的参数验证异常， 在conttoller中没有写result参数时，会进入
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseResult methodArgumentNotValidException(MethodArgumentNotValidException ex, HttpServletRequest request,HttpServletResponse response) throws IOException {
        log.warn("MethodArgumentNotValidException:", ex);
        String defaultMessage = ex.getBindingResult().getFieldError().getDefaultMessage();

        ResponseResult<Object> responseWrap = ResponseResultBuild.build(UserEnum.PARAM_EX, defaultMessage);

        return  responseWrap;
//        }

        //编码防止中文问题
//        response.setContentType("application/json;charset =utf-8");
//        response.getWriter().write(JSON.toJSONString(responseWrap));
    }


    @ExceptionHandler(BindException.class)
    public void bindException(BindException ex, HttpServletRequest request, HttpServletResponse response) throws IOException {
        log.warn("BindException:", ex);
        ResponseResult<Object> responseWrap = null;
        try {
            String msgs = ex.getBindingResult().getFieldError().getDefaultMessage();
            if (StrUtil.isNotEmpty(msgs)) {
                responseWrap = ResponseResultBuild.build(UserEnum.PARAM_EX, msgs);
                //编码防止中文问题
                response.setContentType("application/json;charset =utf-8");
                response.getWriter().write(JSON.toJSONString(responseWrap));
                return;
            }
        } catch (Exception ee) {
        }
        StringBuilder msg = new StringBuilder();
        List<FieldError> fieldErrors = ex.getFieldErrors();
        fieldErrors.forEach((oe) ->
                msg.append("参数:[").append(oe.getObjectName())
                        .append(".").append(oe.getField())
                        .append("]的传入值:[").append(oe.getRejectedValue()).append("]与预期的字段类型不匹配.")
        );
        responseWrap = ResponseResultBuild.build(UserEnum.PARAM_EX, msg);
        //编码防止中文问题
        response.setContentType("application/json;charset =utf-8");
        response.getWriter().write(JSON.toJSONString(responseWrap));
//        return R.result(ExceptionCode.PARAM_EX.getCode(), StrPool.EMPTY, msg.toString()).setPath(request.getRequestURI());
    }

    //表示当请求发生异常时，被ExceptionHandler注释的方法会去处理
    @ExceptionHandler({ConstraintViolationException.class})
    public void validateException(Exception ex, HttpServletResponse response) throws IOException {
        ResponseResult<Object> responseWrap = null;
        //自定义异常
        String msg = StrUtil.EMPTY;
        if(ex instanceof ConstraintViolationException){
            ConstraintViolationException constraintViolationException =
                    (ConstraintViolationException)ex;
            Set<ConstraintViolation<?>> violations =
                    constraintViolationException.getConstraintViolations();
            ConstraintViolation<?> next = violations.iterator().next();
            msg = next.getMessage();
        }else if(ex instanceof BindException){
            BindException bindException = (BindException)ex;
            msg = bindException.getBindingResult().getFieldError().getDefaultMessage();
        }else {
            //系统异常
            responseWrap = ResponseResultBuild.build(BaseEnum.SYSYTEM_FAIL, null);
            msg=JSON.toJSONString(responseWrap);
            log.error("系统异常：{}", ExceptionsUtil.getStackTraceAsString(ex));
        }
        //编码防止中文问题
        response.setContentType("application/json;charset =utf-8");
        response.getWriter().write(msg);
    }


    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public void methodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex, HttpServletRequest request, HttpServletResponse response) throws IOException {
        log.warn("MethodArgumentTypeMismatchException:", ex);
        MethodArgumentTypeMismatchException eee = (MethodArgumentTypeMismatchException) ex;
        StringBuilder msg = new StringBuilder("参数：[").append(eee.getName())
                .append("]的传入值：[").append(eee.getValue())
                .append("]与预期的字段类型：[").append(eee.getRequiredType().getName()).append("]不匹配");

        ResponseResult<Object> responseWrap = ResponseResultBuild.build(UserEnum.PARAM_EX, msg);

//        }

        //编码防止中文问题
        response.setContentType("application/json;charset =utf-8");
        response.getWriter().write(JSON.toJSONString(responseWrap));

//        return R.result(ExceptionCode.PARAM_EX.getCode(), StrPool.EMPTY, msg.toString()).setPath(request.getRequestURI());
    }
}
