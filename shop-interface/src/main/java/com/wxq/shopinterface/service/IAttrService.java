package com.wxq.shopinterface.service;

import com.wxq.bean.Attr;

public interface IAttrService {

  boolean add(Attr attr);

  boolean update(Attr attr);

  boolean delete(Integer id);
}
