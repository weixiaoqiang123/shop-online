package com.wxq.shopinterface.service;

import com.wxq.bean.Order;
import com.wxq.util.OrderInfo;
import com.wxq.util.OrderPreVo;
import com.wxq.util.OrderVo;
import java.util.List;

/**
 * @author weixiaoqiang
 * @date 2021/4/20 15:18
 */
public interface IOrderService {

  List<OrderVo> submitOrderPre(OrderPreVo orderPreVo);

  void submitOrder(OrderInfo orderInfo);

  boolean addOrder(Order order);
}
