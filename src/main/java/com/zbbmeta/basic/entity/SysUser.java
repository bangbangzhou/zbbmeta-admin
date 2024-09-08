package com.zbbmeta.basic.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

import com.zbbmeta.basic.BasePojo;
import lombok.Data;

/**
 * @TableName sys_user
 */
@TableName(value ="sys_user")
@Data
public class SysUser extends BasePojo {
    @TableId(type=IdType.ASSIGN_ID)
    private Long userId;

    private Long deptId;

    private String userName;

    private String nickName;

    private String userType;

    private String email;

    private String phonenumber;

    private String sex;

    private String avatar;

    private String password;

    private String status;

    private String delFlag;

    private String loginIp;

    private Date loginDate;


    private static final long serialVersionUID = 1L;
}