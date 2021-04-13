package com.wxq.enums;

/**
 * @author weixiaoqiang
 * @date 2021/4/13 15:41
 */
public enum OrderStatus {

  WAITING_DELEVER("待发货"),
  DELEVERED("已发货"),
  FINISHED("已收货");

  private String name;

  OrderStatus(String name){
    this.name=name;
  }
}
