package com.wxq.order.service.impl;

import com.alibaba.fastjson.JSON;
import com.wxq.bean.Cart;
import com.wxq.bean.Order;
import com.wxq.bean.OrderDetail;
import com.wxq.bean.Sku;
import com.wxq.enums.OrderStatus;
import com.wxq.shopinterface.mapper.CartMapper;
import com.wxq.shopinterface.mapper.OrderMapper;
import com.wxq.shopinterface.mapper.SkuMapper;
import com.wxq.shopinterface.service.IOrderService;
import com.wxq.util.GoodsVo;
import com.wxq.util.OrderInfo;
import com.wxq.util.OrderPreVo;
import com.wxq.util.OrderVo;
import com.wxq.util.common.CommonUtil;
import com.wxq.util.common.ConstUtil;
import com.wxq.util.common.RedisUtil;
import com.wxq.util.common.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author weixiaoqiang
 * @date 2021/4/20 15:19
 */
@Service
public class OrderServiceImpl implements IOrderService {

  @Autowired
  private OrderMapper orderMapper;

  @Autowired
  private CartMapper cartMapper;

  @Autowired
  private SkuMapper skuMapper;

  @Autowired
  private RedisUtil redisUtil;


  /**
   * 从购物车中查询，根据businessCode分组
   * @param orderPreVo
   * @return
   */
  @Override
  public List<OrderVo> submitOrderPre(OrderPreVo orderPreVo) {
    String account = orderPreVo.getAccount();
    Map<String, List<GoodsVo>> map = orderPreVo.getGoodsVoList().stream().collect(Collectors.groupingBy(GoodsVo::getBusinessName));
    Iterator<String> iterator = map.keySet().iterator();
    List<OrderVo> orderVoList = new ArrayList<>();
    List<GoodsVo> goodsVoList = new ArrayList<>();
    OrderVo orderVo = new OrderVo();
    GoodsVo goodsVo = new GoodsVo();
    while (iterator.hasNext()){
      String businessName = iterator.next();
      orderVo.setBusinessName(businessName);
      // 查询sku
      List<GoodsVo> goodsVoListPre = map.get(businessName);
      for(GoodsVo goodsVo1 : goodsVoListPre){
        goodsVo.setShopNum(goodsVo1.getShopNum());
        String skuCode = goodsVo1.getSkuCode();
        Cart cart = cartMapper.findCart(account, skuCode);
        goodsVo.setSpuName(cart.getSpuName());
        goodsVo.setPrice(cart.getPrice().toString());
        goodsVo.setImagePath(cart.getImagePath());
        goodsVo.setSkuCode(cart.getSkuCode());
        goodsVoList.add(goodsVo);
      }
      // 添加该商家下的所有商品
      orderVo.setGoodsVoList(goodsVoList);
      orderVoList.add(orderVo);
    }
    return orderVoList;
  }

  @Override
  public void submitOrder(OrderInfo orderInfo) {
    List<Order> orderList = new ArrayList<>();
    List<OrderDetail> orderDetailList = new ArrayList<>();
    // 生成订单信息
    Order order = new Order();
    OrderDetail orderDetail = new OrderDetail();
    String account = orderInfo.getAccount();
    List<OrderVo> orderVoList = orderInfo.getOrderVoList();
    order.setAccount(account);
    for(OrderVo orderVo : orderVoList){
      // 生成订单编号
      BigDecimal orderAmount = new BigDecimal(0);
      String orderCode = StringUtil.getCommonCode(2);
      List<GoodsVo> goodsVoList = orderVo.getGoodsVoList();
      orderDetailList.clear();
      for(GoodsVo goodsVo : goodsVoList){
        Integer shopNum = goodsVo.getShopNum();
        orderDetail.setOrderCode(orderCode);
        orderDetail.setShopNum(shopNum);
        orderDetail.setSkuCode(goodsVo.getSkuCode());
        // 计算商品详情总金额
        BigDecimal price = new BigDecimal(goodsVo.getPrice());
        BigDecimal skuAmount = price.multiply(new BigDecimal(shopNum));
        orderDetail.setSkuAmount(skuAmount);
        // 计算订单总金额
        orderAmount.add(skuAmount);
        // 保存订单详情
        orderDetailList.add(orderDetail);
      }
      order.setOrderCode(orderCode);
      order.setOrderAmount(orderAmount);
      order.setOrderStatus(0);
      order.setPayStatus(0);
      order.setCreateBy(account);
      order.setCreateTime(LocalDateTime.now());
      // 保存订单
      order.setOrderDetailList(orderDetailList);
      orderList.add(order);
    }
    // 保存到redis
    Jedis jedis = redisUtil.getJedis();
    // 将订单信息保存30分钟
    jedis.setex(account, ConstUtil.KEY_TIME_OUT, JSON.toJSONString(orderList));
    jedis.close();
  }

  @Override
  public boolean addOrder(Order order) {
    return orderMapper.insert(order) == 1;
  }
}
