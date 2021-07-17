package com.wxq.goods.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.wxq.bean.SpuDetail;
import com.wxq.shopinterface.mapper.SpuDetailMapper;
import com.wxq.shopinterface.service.ISpuDetailService;
import com.wxq.util.aop.CheckResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author weixiaoqiang
 * @date 2021/6/11 16:47
 */
@Service
public class SpuDetailImpl implements ISpuDetailService {

  @Autowired
  private SpuDetailMapper spuDetailMapper;

  @Override
  @CheckResult
  public int add(SpuDetail spuDetail) {
    return spuDetailMapper.insert(spuDetail);
  }

  @Override
  @CheckResult
  public int update(SpuDetail spuDetail) {
    return spuDetailMapper.updateByCondition("spu_detail",
    Wrappers.update().set("info", spuDetail.getInfo()).eq("spu_code", spuDetail.getSpuCode()));
  }
}
