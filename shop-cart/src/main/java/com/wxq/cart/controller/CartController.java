package com.wxq.cart.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wxq.bean.Cart;
import com.wxq.shopinterface.service.ICartService;
import com.wxq.util.CartVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

/**
 * @author weixiaoqiang
 * @date 2021/4/20 15:24
 */
@RestController
@RequestMapping("/cart")
public class CartController {

  @Autowired
  private ICartService cartService;

  @PostMapping
  public Object add(@RequestBody Cart cart){
    if(cartService.add(cart)){
      return new ResponseEntity<>(HttpStatus.CREATED);
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  @PutMapping
  public Object update(@RequestBody Cart cart){
    if(cartService.update(cart)){
      return new ResponseEntity<>(HttpStatus.OK);
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  @DeleteMapping
  public Object deleteBatch(List<Integer> ids){
    if(cartService.deleteBatch(ids)){
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  @GetMapping
  public Object findByPage(@RequestParam Map<String, String> params,
                           @RequestParam("currentPage") Integer page,
                           @RequestParam("lineSize") Integer size){
    return null;
  }

  @GetMapping("/cartList")
  public Object cartList(@RequestParam("currentPage") Integer page,
                         @RequestParam("lineSize") Integer size){
    Page<CartVo> pageInfo = cartService.findByPage(page, size);
    return new ResponseEntity<>(pageInfo, HttpStatus.OK);
  }
}
