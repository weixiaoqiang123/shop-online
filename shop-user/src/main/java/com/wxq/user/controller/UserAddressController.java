package com.wxq.user.controller;

import com.wxq.bean.UserAddress;
import com.wxq.shopinterface.service.IUserAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author weixiaoqiang
 * @date 2021/4/19 17:27
 */
@RestController
@RequestMapping("/user-address")
public class UserAddressController {

  @Autowired
  private IUserAddressService userAddressService;

  @PostMapping
  public Object add(@RequestBody UserAddress userAddress){
    if(userAddressService.add(userAddress)){
      return new ResponseEntity<>(HttpStatus.CREATED);
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  @PutMapping
  public Object update(@RequestBody UserAddress userAddress){
    if(userAddressService.update(userAddress)){
      return new ResponseEntity<>(HttpStatus.OK);
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  @GetMapping("/{id}")
  public Object get(@PathVariable Integer id){
    UserAddress userAddress = userAddressService.get(id);
    return new ResponseEntity<>(userAddress, HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public Object delete(@PathVariable Integer id){
    if(userAddressService.delete(id)){
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  @GetMapping
  public Object findAll(@RequestParam("account") String account){
    List<UserAddress> userAddressList = userAddressService.findAll(account);
    return new ResponseEntity<>(userAddressList, HttpStatus.OK);
  }
}
