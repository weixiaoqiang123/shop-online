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
 * @Date: 2021/4/9 14:24
 */
@Data
@TableName("business_cate")
@ApiModel(value = "BusinessCate")
public class BusinessCate {

  @TableId(value = "id",type = IdType.AUTO)
  private Integer id;

  @TableField("business_code")
  @ApiModelProperty("商家编码")
  private String businessCode;

  @TableField("cate_name")
  @ApiModelProperty("商家分类名称")
  private String cateName;

  @TableField("cate_code")
  @ApiModelProperty("商家分类编码")
  private String cateCode;

  @TableField(exist = false)
  @ApiModelProperty("商家分类下的商品，没有分类的商品使用单独的接口进行查询")
  private List<Spu> spuList;
}
