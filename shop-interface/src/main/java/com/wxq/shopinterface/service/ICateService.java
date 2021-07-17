package com.wxq.shopinterface.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wxq.bean.Cate;
import com.wxq.util.common.BaseTree;

import java.util.List;
import java.util.Map;

public interface ICateService {

  boolean add(Cate cate);

  boolean update(Cate cate);

  boolean logicDelete(String cateCode);

  boolean delete(String cateCode);

  Cate findById(String cateCode);

  List<BaseTree> findAll();

  Page<Cate> findByPage(Map<String, String> cate, Integer currentPage, Integer lineSize);

  List<BaseTree> findThirdCate(Integer targetDeep);
}
