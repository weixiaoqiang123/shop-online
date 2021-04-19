package com.wxq.goods.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.wxq.bean.*;
import com.wxq.shopinterface.mapper.*;
import com.wxq.shopinterface.service.ISkuService;
import com.wxq.shopinterface.service.ISpuService;
import com.wxq.util.SpuAttrValue;
import com.wxq.util.common.Page;
import com.wxq.util.common.PageQuery;
import com.wxq.util.common.StringUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
public class SpuServiceImpl implements ISpuService {

  private static Logger logger = LoggerFactory.getLogger(SpuServiceImpl.class);

  @Autowired
  private SpuMapper spuMapper;

  @Autowired
  private SkuMapper skuMapper;

  @Autowired
  private SpuDetailMapper spuDetailMapper;

  @Autowired
  private SpuAttrMapper spuAttrMapper;

  @Autowired
  private AttrMapper attrMapper;

  @Autowired
  private AttrValueMapper attrValueMapper;

  /**
   * 1. 生成spuCode
   * 2. 处理价格
   * 3. 新增商品基本信息
   * 4. 新增商品详情
   * 5. 新增商品属性和属性值(新增时直接调用接口进行新增，所有流程完成后保存spu_code与attr_id的关系)
   * @param spu
   * @return
   */
  @Override
  @Transactional(rollbackFor = Exception.class)
  public void add(Spu spu) {
    String spuCode = StringUtil.getCode();
    spu.setSpuCode(spuCode);
    String fontOriginalPrice = spu.getFontOriginalPrice();
    spu.setOriginalPrice(new BigDecimal(fontOriginalPrice));
    if(spuMapper.insert(spu) != 1){
      logger.error("商品基本信息新增失败");
      throw new RuntimeException();
    }
    SpuDetail spuDetail = spu.getSpuDetail();
    if(spuDetailMapper.insert(spuDetail) != 1){
      logger.error("商品详细信息新增失败");
      throw new RuntimeException();
    }
    List<Integer> attrIdList = spu.getAttrIdList();
    SpuAttr spuAttr = new SpuAttr();
    spuAttr.setSpuCode(spu.getSpuCode());
    for(int attrId : attrIdList){
      spuAttr.setAttrId(attrId);
      if(spuAttrMapper.insert(spuAttr) != 1){
        logger.error("商品属性关系新增失败");
        throw new RuntimeException();
      }
    }
  }

  /**
   * 1. 处理价格
   * 2. 修改基本信息
   * 3. 修改详情信息
   * 4. 删除spu关系表中该商品的所有记录
   * 5. 新增spu与属性的关系
   * @param spu
   */
  @Override
  @Transactional(rollbackFor = Exception.class)
  public void update(Spu spu) {
    String spuCode = spu.getSpuCode();
    String fontSalePrice = spu.getFontSalePrice();
    if(StringUtils.isNotEmpty(fontSalePrice)){
      spu.setSalePrice(new BigDecimal(fontSalePrice));
    }
    UpdateWrapper<Spu> spuUpdateWrapper = new UpdateWrapper<>();
    spuUpdateWrapper.eq("spu_code", spuCode);
    if(spuMapper.update(spu, spuUpdateWrapper) != 1){
      logger.error("商品基本信息修改失败");
      throw new RuntimeException();
    }
    UpdateWrapper<SpuDetail> spuDetailUpdateWrapper = new UpdateWrapper<>();
    spuDetailUpdateWrapper.eq("spu_code", spuCode);
    SpuDetail spuDetail = spu.getSpuDetail();
    if(spuDetailMapper.update(spuDetail, spuDetailUpdateWrapper) != 1){
      logger.error("商品详细信息修改失败");
      throw new RuntimeException();
    }
    // 查询总记录数
    int count = spuAttrMapper.count(spuCode);
    if(spuAttrMapper.deleteBatch(spuCode) == count){
      logger.error("删除商品属性关系失败");
      throw new RuntimeException();
    }
    List<Integer> attrIdList = spu.getAttrIdList();
    SpuAttr spuAttr = new SpuAttr();
    spuAttr.setSpuCode(spuCode);
    for(int attrId : attrIdList){
      spuAttr.setAttrId(attrId);
      if(spuAttrMapper.insert(spuAttr) != 1){
        logger.error("新增商品属性关系失败");
        throw new RuntimeException();
      }
    }
  }

  /**
   * 1. 删除基本信息
   * 2. 删除sku
   * 3. 删除详细信息
   * 4. 删除关系
   * 5. 删除属性和属性值
   * @param spuCode
   */
  @Override
  @Transactional(rollbackFor = Exception.class)
  public void delete(String spuCode) {
    if(spuMapper.delete(spuCode) != 1){
      logger.error("删除商品基本信息失败");
      throw new RuntimeException();
    }
    // 查询sku总数
    long total = skuMapper.total(spuCode);
    // 根据spuCode做逻辑删除
    if(skuMapper.updateByCondition("sku", Wrappers.update()
    .set("spu_code", spuCode)) != Integer.parseInt(total+"")){
      logger.error("删除sku信息失败");
      throw new RuntimeException();
    }
    if(spuDetailMapper.delete(spuCode) != 1){
      logger.error("删除商品详细信息失败");
      throw new RuntimeException();
    }
    if(spuAttrMapper.deleteBatch(spuCode) != 1){
      logger.error("删除商品属性关系失败");
      throw new RuntimeException();
    }
    // 查询商品的属性和属性值
    Spu spu = spuMapper.findById(spuCode);
    // 获取所有属性
    List<SpuAttr> spuAttrList = spu.getSpuAttrList();
    for(SpuAttr spuAttr : spuAttrList){
      if(attrMapper.deleteById(spuAttr.getAttrId()) != 1){
        logger.error("删除商品属性失败");
        throw new RuntimeException();
      }
      // 使用stream获取该属性下的所有属性值id
      List<Integer> attrValueIdList = spuAttr.getAttrValueList().stream().map(spuAttrValue -> spuAttrValue.getAttrId()).collect(Collectors.toList());
      // 根据属性id删除属性值
      if(attrValueMapper.deleteBatchIds(attrValueIdList) != attrValueIdList.size()){
        logger.error("删除商品属性值失败");
        throw new RuntimeException();
      }
    }
  }

  @Override
  public Spu get(String spuCode) {
    QueryWrapper<Spu> wrapper = new QueryWrapper<>();
    wrapper.eq("spu_code", spuCode);
    return spuMapper.selectOne(wrapper);
  }

  @Override
  public Page<Spu> findByBusinessCateCode(String businessCode, String businessCateCode, Integer page, Integer size) {
    QueryWrapper<Spu> wrapper = new QueryWrapper<>();
    wrapper.eq("business_code", businessCode);
    wrapper.eq("business_cate_code", businessCateCode);
    Map<String, String> params = new HashMap<>();
    params.put("business_code", businessCode);
    params.put("business_cate_code", businessCateCode);
    return PageQuery.query(spuMapper, page, size, params);
  }

  @Override
  public boolean updateSpuStatus(String spuCode, Integer status) {
    return spuMapper.updateByCondition("spu", Wrappers.update()
    .set("status", status).eq("spu_code", spuCode)) == 1;
  }
}
