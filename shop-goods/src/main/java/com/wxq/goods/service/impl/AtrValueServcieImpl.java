package com.wxq.goods.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.wxq.bean.AttrValue;
import com.wxq.shopinterface.mapper.AttrValueMapper;
import com.wxq.shopinterface.service.IAttrValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AtrValueServcieImpl implements IAttrValueService {

  @Autowired
  private AttrValueMapper attrValueMapper;

  @Override
  public boolean add(AttrValue attrValue) {
    return attrValueMapper.insert(attrValue) == 1;
  }

  @Override
  public boolean update(AttrValue attrValue) {
    UpdateWrapper<AttrValue> wrapper = new UpdateWrapper<>();
    wrapper.eq("id", attrValue.getAttrId());
    return attrValueMapper.update(attrValue, wrapper) == 1;
  }

  @Override
  public boolean delete(Integer id) {
    return attrValueMapper.updateByCondition("attr_value", Wrappers.update()
    .set("is_delete", 1).eq("id", id)) == 1;
  }
}
