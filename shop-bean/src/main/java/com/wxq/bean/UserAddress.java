package com.wxq.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author: weixiaoqiang
 * @Date: 2021/4/9 14:02
 */
@Data
@TableName("user_address")
@ApiModel(value = "userAddress", description = "")
public class UserAddress {

  @TableId(value = "id",type = IdType.AUTO)
  private Integer id;

  @TableField("account")
  @ApiModelProperty("账号")
  private String account;

  @TableField("recevie_name")
  @ApiModelProperty("收件人姓名")
  private String receiveName;

  @TableField("phone")
  @ApiModelProperty("收件人电话")
  private String phone;

  @TableField("province_id")
  @ApiModelProperty("省id")
  private Integer provinceId;

  @TableField("city_id")
  @ApiModelProperty("市id")
  private Integer cityId;

  @TableField("county_id")
  @ApiModelProperty("县id")
  private Integer countyId;

  @TableField("town_id")
  @ApiModelProperty("镇id")
  private Integer townId;

  @TableField("address")
  @ApiModelProperty("详细地址")
  private String address;

  @TableField("tag_code")
  @ApiModelProperty("地址标签")
  private String tagCode;
}
