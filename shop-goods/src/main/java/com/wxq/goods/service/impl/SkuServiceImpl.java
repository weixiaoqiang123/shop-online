package com.wxq.goods.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.wxq.bean.*;
import com.wxq.shopinterface.mapper.SkuAttrValueMapper;
import com.wxq.shopinterface.mapper.SkuMapper;
import com.wxq.shopinterface.service.ISkuService;
import com.wxq.util.common.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class SkuServiceImpl implements ISkuService {

  private static Logger logger = LoggerFactory.getLogger(SkuServiceImpl.class);

  @Autowired
  private SkuMapper skuMapper;

  @Autowired
  private SkuAttrValueMapper skuAttrValueMapper;
  /**
   * 1. 生成skuCode
   * 2. 处理价格
   * 3. 设置spuCode
   * 4. 新增sku
   * 5. 新增sku与属性、属性值的关系
   * @param sku
   */
  @Override
  @Transactional(rollbackFor = Exception.class)
  public void add(Sku sku) {
    // 获取spu
    Spu spu = sku.getSpu();
    // 生成skuCode
    String skuCode = StringUtil.getCode();
    sku.setSkuCode(skuCode);
    // 获取前端传入的价格
    String fontPrice = sku.getFontPrice();
    sku.setPrice(new BigDecimal(fontPrice));
    sku.setSpuCode(spu.getSpuCode());
    if(skuMapper.insert(sku) != 1){
      logger.error("新增sku失败");
      throw new RuntimeException();
    }
    processSkuAttrValue(spu, skuCode);
  }

  @Override
  public void update(Sku sku) {
//    String skuCode = sku.getSkuCode();
//    String fontPrice = sku.getFontPrice();
//    sku.setPrice(new BigDecimal(fontPrice));
//    UpdateWrapper<Sku> skuWrapper = new UpdateWrapper<>();
//    skuWrapper.eq("sku_code", skuCode);
//    if(skuMapper.update(sku, skuWrapper) != 1){
//      logger.error("修改sku失败");
//      throw new RuntimeException();
//    }
//    // 查询记录条数
//    long total = skuAttrValueMapper.total();
//    // 根据skuCode删除所有关系
//    if(skuAttrValueMapper.deleteBatch(skuCode) != Integer.parseInt(total+"")){
//      logger.error("删除sku关系失败");
//      throw new RuntimeException();
//    }
//    // 新增关系
//    processSkuAttrValue(sku.getSpu(), skuCode);
  }

  @Override
  public boolean delete(String skuCode) {
    return skuMapper.updateByCondition("sku", Wrappers.update()
    .set("is_delete", 1).eq("sku_code", skuCode)) == 1;
  }

  @Override
  public Sku get(String skuCode) {
    QueryWrapper<Sku> wrapper = new QueryWrapper<>();
    wrapper.eq("sku_code", skuCode);
    return skuMapper.selectOne(wrapper);
  }

  @Override
  public boolean updateStore(String skuCode, Integer store) {
    return skuMapper.updateByCondition("sku", Wrappers.update()
    .set("store", store).eq("sku_code", skuCode)) == 1;
  }

  /**
   * 新增sku与属性、属性值的关系
   * @param spu
   * @param skuCode
   */
  private void processSkuAttrValue(Spu spu, String skuCode) {
    SkuAttrValue skuAttrValue = new SkuAttrValue();
    // 获取所有的属性
    List<SpuAttr> spuAttrList = spu.getSpuAttrList();
    skuAttrValue.setSkuCode(skuCode);
    for(SpuAttr spuAttr : spuAttrList){
      // 设置属性id
      skuAttrValue.setAttrId(spuAttr.getAttrId());
      // 获取属性下的属性集合
      List<AttrValue> attrValueList = spuAttr.getAttrValueList();
      for(AttrValue attrValue : attrValueList){
        // 设置属性值id
        skuAttrValue.setValueId(attrValue.getId());
        if(skuAttrValueMapper.insert(skuAttrValue) != 1){
          throw new RuntimeException();
        }
      }
    }
  }
}
