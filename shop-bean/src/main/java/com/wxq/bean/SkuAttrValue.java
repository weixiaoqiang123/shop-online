package com.wxq.bean;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author weixiaoqiang
 * @date 2021/4/13 15:10
 */
@Data
@TableName("sku_attr_value")
@ApiModel("SkuAttrValue")
public class SkuAttrValue {

  @TableField("id")
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
  private Attr attr;

  @TableField(exist = false)
  private AttrValue attrValue;
}
