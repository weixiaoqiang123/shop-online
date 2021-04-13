package com.wxq.bean;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author: weixiaoqiang
 * @Date: 2021/4/9 11:46
 */
@Data
@TableName("user")
@ApiModel(value = "user", description = "")
public class User {

  @TableField("id")
  @ApiModelProperty("id")
  private Integer id;

  @TableField("nickname")
  @ApiModelProperty("昵称")
  private Integer nickname;

  @TableField("password")
  @ApiModelProperty("密码，前端加密后传入")
  private String password;

  @TableField("balance")
  @ApiModelProperty("余额")
  private BigDecimal balance;

  @TableField("account")
  @ApiModelProperty("账号")
  private String account;

  @TableField("role")
  @ApiModelProperty("角色 0 系统 1 商家 2 用户")
  private Integer role;

  @TableField("methods")
  @ApiModelProperty("注册方式 0 邮箱 1 手机")
  private Integer method;

  @TableField("name")
  @ApiModelProperty("真实姓名")
  private String name;

  @TableField("head_img")
  @ApiModelProperty("头像图片路径")
  private String headImg;

  @TableField(exist = false)
  @ApiModelProperty("前端传入的余额")
  private String fontBalance;
}
