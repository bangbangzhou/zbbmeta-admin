package com.zbbmeta.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: springboot葵花宝典
 * @Github: https://github.com/bangbangzhou
 * @description: TODO
 */
@MapperScan("com.zbbmeta.mapper")
@Configuration
public class MybatisPlusConfig {
}
