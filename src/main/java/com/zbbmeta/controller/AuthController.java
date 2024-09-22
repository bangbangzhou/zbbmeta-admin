package com.zbbmeta.controller;

import cn.hutool.core.util.StrUtil;
import com.zbbmeta.basic.ResponseResult;
import com.zbbmeta.basic.dto.LoginDTO;
import com.zbbmeta.enums.UserEnum;
import com.zbbmeta.service.SysUserService;
import com.zbbmeta.service.impl.AuthService;
import com.zbbmeta.utils.ResponseResultBuild;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @Author: springboot葵花宝典
 * @Github: https://github.com/bangbangzhou
 * @description: TODO
 */

@Api(tags = "用户登录")
@RestController
public class AuthController {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private AuthService authService;
    @PostMapping(value = "${jwt.route.login}")
    public ResponseResult<String> login(@RequestBody LoginDTO loginDTO) {

        String login = authService.login(loginDTO.getUsername(), loginDTO.getPassword());
        return ResponseResultBuild.build(UserEnum.SUCCEED,login);
    }
}
