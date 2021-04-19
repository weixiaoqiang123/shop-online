package com.wxq.shopinterface.service;

import com.wxq.bean.AttrValue;

public interface IAttrValueService {

  boolean add(AttrValue attrValue);

  boolean update(AttrValue attrValue);

  boolean delete(Integer id);
}
