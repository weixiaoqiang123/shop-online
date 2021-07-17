package com.wxq.order.controller;

import com.wxq.bean.Order;
import com.wxq.shopinterface.service.IOrderService;
import com.wxq.util.OrderInfo;
import com.wxq.util.OrderPreVo;
import com.wxq.util.OrderVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author weixiaoqiang
 * @date 2021/4/20 15:02
 * 下单流程
 * 购物车选中商品 => 结算 => 提交订单（调用submitOrder）=> 支付 => 支付成功新增订单
 */
@RestController
@RequestMapping("/order")
public class OrderController {

  @Autowired
  private IOrderService orderService;

  /**
   * 将购物车结算商品按商家拆分返回
   * @return
   */
  @PostMapping("/submitPre")
  public Object submitPre(@RequestBody OrderPreVo orderPreVo){
    List<OrderVo> orderVoList = orderService.submitOrderPre(orderPreVo);
    return new ResponseEntity<>(orderVoList, HttpStatus.OK);
  }

  /**
   * 将订单保存到redis中，支付成功后删除
   * @return
   */
  @PostMapping("/submitOrder")
  public Object submitOrder(@RequestBody OrderInfo orderInfo){
    orderService.submitOrder(orderInfo);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @PostMapping
  public boolean addOrder(@RequestBody Order order){
    return orderService.addOrder(order);
  }

  public Object get(){
    return null;
  }
}
