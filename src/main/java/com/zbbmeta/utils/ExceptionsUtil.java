package com.zbbmeta.utils;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @Author: springboot葵花宝典
 * @Github: https://github.com/bangbangzhou
 * @description: 异常转换工具
 */
public class ExceptionsUtil {

    /**
     *
     * <b>方法名：</b>：getStackTraceAsString<br>
     * <b>功能说明：</b>：将ErrorStack转化为String<br>
     */
    public static String getStackTraceAsString(Exception e) {
        StringWriter stringWriter = new StringWriter();
        e.printStackTrace(new PrintWriter(stringWriter));
        return stringWriter.toString();
    }

}
