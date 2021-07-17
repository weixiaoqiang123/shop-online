package com.wxq.shopinterface.service;

import com.wxq.bean.UserAddress;

import java.util.List;

/**
 * @author weixiaoqiang
 * @date 2021/4/19 17:28
 */
public interface IUserAddressService {

  boolean add(UserAddress userAddress);

  boolean update(UserAddress userAddress);

  boolean delete(Integer id);

  UserAddress get(Integer id);

  List<UserAddress> findAll(String account);
}
