package com.zbbmeta.controller;

import com.zbbmeta.basic.ResponseResult;
import com.zbbmeta.basic.entity.SysUser;
import com.zbbmeta.enums.UserEnum;
import com.zbbmeta.service.SysUserService;
import com.zbbmeta.utils.ResponseResultBuild;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: springboot葵花宝典
 * @Github: https://github.com/bangbangzhou
 * @description: TODO
 */
@Api(tags = "用户控制器")
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
    @ApiOperation(value = "根据ID查询用户信息", notes = "根据ID查询用户信息")
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
    @ApiOperation(value = "新增用户", notes = "新增用户")
    @PostMapping("/insert")
    public ResponseResult insert(@RequestBody @Validated SysUser userEntity) {

        return    ResponseResultBuild.build(UserEnum.SUCCEED, userService.save(userEntity));
    }

    /**
     * 删除用户
     *
     * @param userId 用户ID
     * @return 影响行数
     */
    @ApiOperation(value = "删除用户",notes = "删除用户")
    @ApiImplicitParam(paramType = "path",name = "userId",value = "用户Id",example = "1",dataType = "Long")
    @PostMapping("/deleteById/{userId}")
    public ResponseResult deleteById(@PathVariable("userId")  Long userId) {
        return    ResponseResultBuild.build(UserEnum.SUCCEED, userService.removeById(userId));
    }

}
