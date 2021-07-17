package com.wxq.util.exception;

/**
 * @author weixiaoqiang
 * @date 2021/4/19 16:23
 */
public class GoodsAmountNotEnoughExeption extends RuntimeException{

  public GoodsAmountNotEnoughExeption() {
  }

  public GoodsAmountNotEnoughExeption(String message) {
    super(message);
  }
}
