package com.wxq.shopinterface.service;

import com.wxq.bean.Spu;
import com.wxq.util.common.Page;

public interface ISpuService {

  void add(Spu spu);

  void update(Spu spu);

  void delete(String spuCode);

  Spu get(String spuCode);

  Page<Spu> findByBusinessCateCode(String businessCode, String businessCateCode, Integer page, Integer size);

  boolean updateSpuStatus(String spuCode, Integer status);
}
