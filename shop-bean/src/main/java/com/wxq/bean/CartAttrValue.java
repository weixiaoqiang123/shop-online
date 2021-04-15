package com.wxq.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author weixiaoqiang
 * @date 2021/4/13 15:33
 */
@Data
@TableName("shop_cart_attr_value")
@ApiModel("购物车中sku与属性、属性值的关系")
public class CartAttrValue {

  @TableId(value = "id",type = IdType.AUTO)
  private Integer id;

  @TableField("sku_code")
  private String skuCode;

  @TableField("attr_id")
  @ApiModelProperty("属性id")
  private Integer attrId;

  @TableField("value_id")
  @ApiModelProperty("属性值id")
  private Integer valueId;

  @TableField(exist = false)
  @ApiModelProperty("购物车sku属性")
  private Attr attr;

  @TableField(exist = false)
  @ApiModelProperty("购物车sku属性值")
  private AttrValue attrValue;
}
