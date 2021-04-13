package com.wxq.bean;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author weixiaoqiang
 * @date 2021/4/13 15:35
 */
@Data
@TableName("shop_order")
@ApiModel("订单")
public class Order {

  @TableField("id")
  private Integer id;

  @TableField("order_code")
  @ApiModelProperty("订单编号")
  private String orderCode;

  @TableField("account")
  @ApiModelProperty("下单人账号")
  private String account;

  /**
   * @see com.wxq.enums.OrderStatus
   */
  @TableField("order_status")
  @ApiModelProperty("订单状态: 0 待发货 1 已发货 2 已收货")
  private Integer orderStatus;

  /**
   * @see com.wxq.enums.PayStatus
   */
  @TableField("pay_status")
  @ApiModelProperty("支付状态: 0 未支付 1 支付中 2 已支付")
  private Integer payStatus;

  @TableField("order_amount")
  @ApiModelProperty("订单总金额")
  private BigDecimal orderAmount;

  @TableField("create_time")
  @ApiModelProperty("订单创建时间")
  private LocalDateTime createTime;

  @TableField("create_by")
  @ApiModelProperty("创建人")
  private String createBy;

  @TableField(exist = false)
  @ApiModelProperty("订单对应的订单详情")
  private List<OrderDetail> orderDetailList;
}
