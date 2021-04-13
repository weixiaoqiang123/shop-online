package com.wxq.enums;

/**
 * @author weixiaoqiang
 * @date 2021/4/13 15:48
 */
public enum PayStatus {

  PAID("未支付"),
  UNPAID("已支付"),
  PAYING("支付中");

  private String comment;

  PayStatus(String comment){
    this.comment = comment;
  }
}
