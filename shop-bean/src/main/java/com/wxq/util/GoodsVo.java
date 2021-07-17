package com.wxq.util;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author weixiaoqiang
 * @date 2021/4/21 10:00
 */
@Data
public class GoodsVo {

  @ApiModelProperty("提交订单")
  private String spuName;

  @ApiModelProperty("订单预处理 提交订单")
  private String skuCode;

  @ApiModelProperty("订单预处理 提交订单")
  private Integer shopNum;

  @ApiModelProperty("订单预处理")
  private String businessName;

  @ApiModelProperty("提交订单")
  private String price;

  @ApiModelProperty("提交订单")
  private String imagePath;
}
