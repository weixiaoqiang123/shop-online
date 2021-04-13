package com.wxq.bean;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author weixiaoqiang
 * @date 2021/4/13 14:40
 */
@Data
@TableName("spu")
@ApiModel("spu")
public class Spu {

  @TableField("id")
  private Integer id;

  @TableField("spu_name")
  @ApiModelProperty("spu名称")
  private String spuName;

  @TableField("spu_code")
  @ApiModelProperty("spu编码")
  private String spuCode;

  @TableField("spu_introduction")
  @ApiModelProperty("商品描述")
  private String spuIntroduction;

  @TableField("original_price")
  @ApiModelProperty("商品原价")
  private BigDecimal originalPrice;

  @TableField("sale_price")
  @ApiModelProperty("活动优惠价")
  private BigDecimal salePrice;

  @TableField("sale_num")
  @ApiModelProperty("销售数量")
  private Integer saleNum;

  @TableField("cate_code")
  @ApiModelProperty("分类编码")
  private String cateCode;

  @TableField("image_path")
  @ApiModelProperty("商品封面图片路径")
  private String imagePath;

  @TableField(exist = false)
  @ApiModelProperty("前端传入的原价")
  private String fontOriginalPrice;

  @TableField(exist = false)
  @ApiModelProperty("前端传入的售价")
  private String fontSalePrice;

  @TableField(exist = false)
  @ApiModelProperty("spu拥有的属性")
  private List<SpuAttr> spuAttrList;
}
