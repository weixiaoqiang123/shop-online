package com.wxq.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
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

  @TableId(value = "id",type = IdType.AUTO)
  private Integer id;

  @TableField("spu_name")
  @ApiModelProperty("spu名称")
  private String spuName;

  @TableField("spu_code")
  @ApiModelProperty("spu编码")
  private String spuCode;

  @TableField("spu_status")
  @ApiModelProperty("商品状态: 0 未发布 1 上架 2 下架")
  private Integer status;

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

  @TableField("business_code")
  @ApiModelProperty("商家编码")
  private String businessCode;

  @TableField("business_cate_code")
  @ApiModelProperty("商家分类编码")
  private String businessCateCode;

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

  @TableField(exist = false)
  private SpuDetail spuDetail;

  @TableField(exist = false)
  @ApiModelProperty("spu所属商家")
  private Business business;

  @TableField(exist = false)
  @ApiModelProperty("新增商品的属性id集合")
  private List<Integer> attrIdList;

  @TableField(exist = false)
  @ApiModelProperty("商品轮播图图片路径")
  private List<String> imageList;
}
