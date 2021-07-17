package com.wxq.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author weixiaoqiang
 * @date 2021/4/13 15:28
 */
@Data
@TableName("shop_cart")
@ApiModel("购物车")
public class Cart {

  @TableId(value = "id",type = IdType.AUTO)
  private Integer id;

  @TableField("account")
  @ApiModelProperty("购买人账号")
  private String account;

  @TableField("spu_name")
  @ApiModelProperty("spu名称")
  private String spuName;

  @TableField("spu_introduction")
  @ApiModelProperty("商品描述")
  private String spuIntroduction;

  @TableField("sku_code")
  @ApiModelProperty("sku_code")
  private String skuCode;

  @TableField("price")
  @ApiModelProperty("添加到购物车sku的价格")
  private BigDecimal price;

  @TableField("shop_num")
  @ApiModelProperty("购买数量")
  private Integer shopNum;

  @TableField("business_name")
  @ApiModelProperty("商家名称")
  private String businessName;

  @TableField("business_code")
  @ApiModelProperty("商家编码")
  private String businessCode;

  @ApiModelProperty("将sku的图片路径作为该图片路径")
  @TableField("image_path")
  private String imagePath;

  @TableField("create_time")
  @ApiModelProperty("添加到购物车时间")
  private LocalDateTime createTime;

  @TableField(exist = false)
  @ApiModelProperty("前端传入的价格")
  private String fontPrice;

  @TableField(exist = false)
  @ApiModelProperty("购物车中商品的属性、属性值")
  private List<CartAttrValue> attrValueList;
}
