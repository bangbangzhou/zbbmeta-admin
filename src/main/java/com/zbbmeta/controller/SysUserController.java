package com.zbbmeta.controller;

import com.zbbmeta.basic.ResponseResult;
import com.zbbmeta.basic.entity.SysUser;
import com.zbbmeta.enums.UserEnum;
import com.zbbmeta.service.SysUserService;
import com.zbbmeta.utils.ResponseResultBuild;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: springboot葵花宝典
 * @Github: https://github.com/bangbangzhou
 * @description: TODO
 */

@RestController
@RequestMapping("/user")
public class SysUserController {

    @Autowired
    private SysUserService userService;

    /**
     * 通过id查询用户信息
     *
     * @param id 系统ID
     * @return 用户信息
     */
    @GetMapping("/findById")
    public ResponseResult findById(Long id) {
        return ResponseResultBuild.build(UserEnum.SUCCEED,userService.getById(id));
    }


    /**
     * 添加用户
     *
     * @param userEntity 用户实体
     * @return 影响行数
     */
    @PostMapping("/insert")
    public ResponseResult insert(@RequestBody SysUser userEntity) {

        return    ResponseResultBuild.build(UserEnum.SUCCEED, userService.save(userEntity));
    }

    /**
     * 删除用户
     *
     * @param id 用户ID
     * @return 影响行数
     */
    @PostMapping("/deleteById")
    public ResponseResult deleteById(@RequestBody  Long id) {
        return    ResponseResultBuild.build(UserEnum.SUCCEED, userService.removeById(id));
    }

}
