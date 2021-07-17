package com.wxq.shopinterface.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wxq.bean.Spu;
import com.wxq.util.common.ResponseVo;

import java.util.Map;

public interface ISpuService {

  String add(Spu spu);

  void update(Spu spu);

  void delete(String spuCode);

  Spu get(String spuCode);

  void findByPage(Map<String, String> map, Integer page, Integer size);

  Page<Spu> findByBusinessCateCode(String businessCode, String businessCateCode, Integer page, Integer size);

  boolean updateSpuStatus(String spuCode, Integer status);
}
