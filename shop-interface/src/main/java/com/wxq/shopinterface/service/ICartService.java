package com.wxq.shopinterface.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wxq.bean.Cart;
import com.wxq.util.CartVo;

import java.util.List;
import java.util.Map;

/**
 * @author weixiaoqiang
 * @date 2021/4/20 15:24
 */
public interface ICartService {

  boolean add(Cart cart);

  boolean update(Cart cart);

  boolean deleteBatch(List<Integer> ids);

  Page<Cart> findByPage(Map<String, String> params, Integer page, Integer size);

  Page<CartVo> findByPage(Integer page, Integer size);
}
