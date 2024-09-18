package com.zbbmeta.basic.entity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;

import java.io.Serializable;

import java.lang.reflect.Type;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zbbmeta.basic.BasePojo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
* 用户信息表
* @TableName sys_user
*/
@ApiModel(description = "用户对象 sys_user")
@TableName(value ="sys_user")
@Data
public class SysUser extends BasePojo {

    /**
    * 用户ID
    */
    @ApiModelProperty("用户ID")
    @TableId(type= IdType.ASSIGN_ID)
    private Long userId;
    /**
    * 部门ID
    */
    @ApiModelProperty("部门ID")
    private Long deptId;
    /**
    * 用户账号
    */
    @NotBlank(message="[用户账号]不能为空")
    @Size(max= 30,message="编码长度不能超过30")
    @ApiModelProperty("用户账号")
    @Length(max= 30,message="编码长度不能超过30")
    private String userName;
    /**
    * 用户昵称
    */
    @NotBlank(message="[用户昵称]不能为空")
    @Size(max= 30,message="编码长度不能超过30")
    @ApiModelProperty("用户昵称")
    @Length(max= 30,message="编码长度不能超过30")
    private String nickName;
    /**
    * 用户类型（00系统用户）
    */
    @Size(max= 2,message="编码长度不能超过2")
    @ApiModelProperty("用户类型（00系统用户）")
    @Length(max= 2,message="编码长度不能超过2")
    private String userType;
    /**
    * 用户邮箱
    */
    @Size(max= 50,message="编码长度不能超过50")
    @ApiModelProperty("用户邮箱")
    @Length(max= 50,message="编码长度不能超过50")
    @NotBlank(message="[用户邮箱]不能为空")
    private String email;
    /**
    * 手机号码
    */
    @Size(max= 11,message="编码长度不能超过11")
    @ApiModelProperty("手机号码")
    @Length(max= 11,message="编码长度不能超过11")
    private String phonenumber;
    /**
    * 用户性别（0男 1女 2未知）
    */
    @ApiModelProperty("用户性别（0男 1女 2未知）")
    private String sex;
    /**
    * 头像地址
    */
    @Size(max= 100,message="编码长度不能超过100")
    @ApiModelProperty("头像地址")
    @Length(max= 100,message="编码长度不能超过100")
    private String avatar;
    /**
    * 密码
    */
    @Size(max= 100,message="编码长度不能超过100")
    @ApiModelProperty("密码")
    @Length(max= 100,message="编码长度不能超过100")
    private String password;
    /**
    * 帐号状态（0正常 1停用）
    */
    @ApiModelProperty("帐号状态（0正常 1停用）")
    private String status;
    /**
    * 删除标志（0代表存在 2代表删除）
    */
    @ApiModelProperty("删除标志（0代表存在 2代表删除）")
    private String delFlag;
    /**
    * 最后登录IP
    */
    @Size(max= 128,message="编码长度不能超过128")
    @ApiModelProperty("最后登录IP")
    @Length(max= 128,message="编码长度不能超过128")
    private String loginIp;
    /**
    * 最后登录时间
    */
    @ApiModelProperty("最后登录时间")
    private Date loginDate;


}
