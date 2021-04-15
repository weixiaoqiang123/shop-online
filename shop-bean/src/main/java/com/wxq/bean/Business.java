package com.wxq.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author: weixiaoqiang
 * @Date: 2021/4/9 14:21
 */
@Data
@TableName("business")
@ApiModel(value = "Business", description = "")
public class Business {

  @TableId(value = "id",type = IdType.AUTO)
  private Integer id;

  @TableField("business_name")
  @ApiModelProperty("商家名称")
  private String businessName;

  @TableField("business_code")
  @ApiModelProperty("商家编码")
  private String businessCode;

  @TableField("account")
  @ApiModelProperty("账号")
  private String account;

  @TableField("address")
  @ApiModelProperty("商家地址")
  private String address;

  @TableField(exist = false)
  @ApiModelProperty("商家下的分类")
  private List<BusinessCate> businessCateList;
}
