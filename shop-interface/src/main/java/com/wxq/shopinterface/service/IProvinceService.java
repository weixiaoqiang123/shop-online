package com.wxq.shopinterface.service;

import com.wxq.bean.Province;
import com.wxq.util.common.BaseTree;

import java.util.List;

/**
 * @author weixiaoqiang
 * @date 2021/4/20 14:25
 */
public interface IProvinceService {

  List<Province> findAll();
}
