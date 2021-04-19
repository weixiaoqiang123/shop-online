package com.wxq.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.wxq.bean.UserAddress;
import com.wxq.shopinterface.mapper.UserAddressMapper;
import com.wxq.shopinterface.service.IUserAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author weixiaoqiang
 * @date 2021/4/19 17:27
 */
@Service
public class UserAddressServiceImpl implements IUserAddressService {

  @Autowired
  private UserAddressMapper userAddressMapper;

  @Override
  public boolean add(UserAddress userAddress) {
    return userAddressMapper.insert(userAddress) == 1;
  }

  @Override
  public boolean update(UserAddress userAddress) {
    return userAddressMapper.updateById(userAddress) == 1;
  }

  @Override
  public boolean delete(Integer id) {
    return userAddressMapper.deleteById(id) == 1;
  }

  @Override
  public UserAddress get(Integer id) {
    return userAddressMapper.selectById(id);
  }

  @Override
  public List<UserAddress> findAll(String account) {
    QueryWrapper<UserAddress> wrapper = new QueryWrapper<>();
    wrapper.eq("account", account);
    return userAddressMapper.selectList(wrapper);
  }
}
