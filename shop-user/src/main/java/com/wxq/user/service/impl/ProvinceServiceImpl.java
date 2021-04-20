package com.wxq.user.service.impl;

import com.wxq.bean.Province;
import com.wxq.shopinterface.mapper.ProvinceMapper;
import com.wxq.shopinterface.service.IProvinceService;
import com.wxq.util.common.BaseTree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author weixiaoqiang
 * @date 2021/4/20 14:25
 */
@Service
public class ProvinceServiceImpl implements IProvinceService {

  @Autowired
  private ProvinceMapper provinceMapper;

  @Override
  public List<Province> findAll() {
    return provinceMapper.findAll();
  }
}
