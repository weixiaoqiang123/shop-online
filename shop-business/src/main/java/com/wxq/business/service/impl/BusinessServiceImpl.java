package com.wxq.business.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.wxq.bean.Business;
import com.wxq.shopinterface.mapper.BusinessMapper;
import com.wxq.shopinterface.service.IBusinessService;
import com.wxq.util.common.Page;
import com.wxq.util.common.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class BusinessServiceImpl implements IBusinessService {

  @Autowired
  private BusinessMapper businessMapper;

  @Override
  public boolean add(Business business) {
    business.setIsDelete(0);
    return businessMapper.insert(business) == 1;
  }

  @Override
  public boolean update(Business business) {
    UpdateWrapper<Business> wrapper = new UpdateWrapper<>();
    wrapper.eq("business_code", business.getBusinessCode());
    return businessMapper.update(business, wrapper) == 1;
  }

  @Override
  public boolean delete(String businessCode) {
    UpdateWrapper<Business> wrapper = new UpdateWrapper<>();
    wrapper.eq("business_code", businessCode);
    return businessMapper.delete(wrapper) == 1;
  }

  @Override
  public boolean logicDelete(String businessCode) {
    return businessMapper.updateByCondition("business", Wrappers.update()
    .set("is_delete", 1).eq("business_code", businessCode)) == 1;
  }

  @Override
  public Page<Business> findByPage(Map<String, String> business, Integer currentPage, Integer lineSize) {
    return PageQuery.query(businessMapper, currentPage, lineSize, business);
  }

  @Override
  public Business get(String businessCode) {
    QueryWrapper<Business> wrapper = new QueryWrapper<>();
    wrapper.eq("business_code", businessCode);
    return businessMapper.selectOne(wrapper);
  }
}
