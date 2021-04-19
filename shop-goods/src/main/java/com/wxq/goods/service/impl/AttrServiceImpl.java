package com.wxq.goods.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.wxq.bean.Attr;
import com.wxq.shopinterface.mapper.AttrMapper;
import com.wxq.shopinterface.service.IAttrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AttrServiceImpl implements IAttrService {

  @Autowired
  private AttrMapper attrMapper;

  @Override
  public boolean add(Attr attr) {
    attr.setIsDelete(0);
    return attrMapper.insert(attr) == 1;
  }

  @Override
  public boolean update(Attr attr) {
    return attrMapper.updateById(attr) == 1;
  }

  @Override
  public boolean delete(Integer id) {
    return attrMapper.updateByCondition("attr", Wrappers.update()
    .set("is_delete", 1).eq("id", id)) == 1;
  }
}
