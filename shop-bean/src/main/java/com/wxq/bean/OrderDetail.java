package com.wxq.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author weixiaoqiang
 * @date 2021/4/13 15:53
 */
@Data
@TableName("order_detail")
@ApiModel("订单详情")
public class OrderDetail {

  @TableId(value = "id",type = IdType.AUTO)
  private Integer id;

  @TableField("order_code")
  @ApiModelProperty("订单编码")
  private String orderCode;

  @TableField("sku_code")
  @ApiModelProperty("sku编码")
  private String skuCode;

  @TableField("shop_num")
  @ApiModelProperty("购买数量")
  private Integer shopNum;

  @TableField("sku_amount")
  @ApiModelProperty("商品总金额")
  private BigDecimal skuAmount;
}
