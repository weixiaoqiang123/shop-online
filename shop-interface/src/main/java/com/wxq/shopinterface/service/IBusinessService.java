package com.wxq.shopinterface.service;

import com.wxq.bean.Business;
import com.wxq.util.common.Page;

import java.util.Map;

public interface IBusinessService {

  boolean add(Business business);

  boolean update(Business business);

  boolean delete(String businessCode);

  boolean logicDelete(String businessCode);

  Page<Business> findByPage(Map<String, String> business, Integer currentPage, Integer lineSize);

  Business get(String businessCode);
}
