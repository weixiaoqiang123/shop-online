package com.wxq.cart.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wxq.bean.Cart;
import com.wxq.shopinterface.mapper.CartMapper;
import com.wxq.shopinterface.service.ICartService;
import com.wxq.util.CartVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author weixiaoqiang
 * @date 2021/4/20 15:24
 */
@Service
public class CartServiceImpl implements ICartService {

  @Autowired
  private CartMapper cartMapper;

  @Override
  public boolean add(Cart cart) {
    return cartMapper.insert(cart) == 1;
  }

  @Override
  public boolean update(Cart cart) {
    return cartMapper.updateByCondition("shop_cart", Wrappers.update()
    .set("shop_num", cart.getShopNum()).eq("id", cart.getId())) == 1;
  }

  @Override
  public boolean deleteBatch(List<Integer> ids) {
    return cartMapper.deleteBatchIds(ids) == ids.size();
  }

  @Override
  public Page<Cart> findByPage(Map<String, String> params, Integer page, Integer size) {
    return null;
  }

  @Override
  public Page<CartVo> findByPage(Integer page, Integer size) {
//    Page<CartVo> pageInfo = new Page<>();
//    // 查询总记录数
//    long total = cartMapper.total();
//    long pages = (total + size - 1)/size;
//    pageInfo.setPages(Integer.parseInt(pages+""));
//    pageInfo.setPage(page);
//    pageInfo.setSize(size);
//    List<CartVo> cartVoList = new ArrayList<>();
//    List<Cart> cartList = cartMapper.findByPage(null, page, size);
//    Map<String, List<Cart>> map = cartList.stream().collect(Collectors.groupingBy(Cart::getBusinessName));
//    Iterator<String> iterator = map.keySet().iterator();
//    CartVo cartVo = new CartVo();
//    while (iterator.hasNext()){
//      String businessName = iterator.next();
//      cartVo.setBusinessName(businessName);
//      cartVo.setCartList(map.get(businessName));
//      cartVoList.add(cartVo);
//    }
//    pageInfo.setData(cartVoList);
    return null;
  }
}
