package com.wxq.shopinterface.service;

import com.wxq.bean.Sku;

public interface ISkuService {

  void add(Sku sku);

  void update(Sku sku);

  boolean delete(String skuCode);

  Sku get(String skuCode);

  boolean updateStore(String skuCode, Integer store);
}
