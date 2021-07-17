package com.wxq.goods.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wxq.bean.*;
import com.wxq.shopinterface.mapper.*;
import com.wxq.shopinterface.service.ISpuService;
import com.wxq.util.common.ErrorCode;
import com.wxq.util.common.ResponseVo;
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
  private SpuImagesMapper spuImagesMapper;

  @Autowired
  private AttrMapper attrMapper;

  @Autowired
  private AttrValueMapper attrValueMapper;

  /**
   * 1. 生成spuCode
   * 2. 处理原价
   * 3. 新增商品基本信息
   * @param spu
   * @return 新增成功返回spuCode
   */
  @Override
  public String add(Spu spu) {
    String spuCode = StringUtil.getCode();
    spu.setSpuCode(spuCode);
    String originalPrice = spu.getFontOriginalPrice();
    // 新增时原价和售价一致
    BigDecimal price = new BigDecimal(originalPrice);
    spu.setOriginalPrice(price);
    spu.setSalePrice(price);
    if(spuMapper.insert(spu) == 1){
      return spuCode;
    }
    return null;
  }

  @Override
  public void update(Spu spu) {
    String spuCode = spu.getSpuCode();
    // 只能修改售价
    spu.setSalePrice(new BigDecimal(spu.getFontSalePrice()));
    UpdateWrapper<Spu> spuUpdateWrapper = new UpdateWrapper<>();
    spuUpdateWrapper.eq("spu_code", spuCode);
    if(spuMapper.update(spu, spuUpdateWrapper) != 1){
      logger.error("商品基本信息修改失败");
      throw new RuntimeException();
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
  public void delete(String spuCode) {
  }

  @Override
  public Spu get(String spuCode) {
    QueryWrapper<Spu> wrapper = new QueryWrapper<>();
    wrapper.eq("spu_code", spuCode);
    return spuMapper.selectOne(wrapper);
  }

  @Override
  public void findByPage(Map<String, String> map, Integer page, Integer size) {
    QueryWrapper<Spu> wrapper = new QueryWrapper<>();
  }

  @Override
  public Page<Spu> findByBusinessCateCode(String businessCode, String businessCateCode, Integer page, Integer size) {
    QueryWrapper<Spu> wrapper = new QueryWrapper<>();
    wrapper.eq("business_code", businessCode);
    wrapper.eq("business_cate_code", businessCateCode);
    Map<String, String> params = new HashMap<>();
    params.put("business_code", businessCode);
    params.put("business_cate_code", businessCateCode);
    return null;
  }

  @Override
  public boolean updateSpuStatus(String spuCode, Integer status) {
    return spuMapper.updateByCondition("spu", Wrappers.update()
    .set("status", status).eq("spu_code", spuCode)) == 1;
  }

  private void addSpuImages(Spu spu, String spuCode) {
    List<String> imageList = spu.getImageList();
    SpuImages spuImages = new SpuImages();
    spuImages.setSpuCode(spuCode);
    for(String path : imageList){
      spuImages.setImagePath(path);
      if(spuImagesMapper.insert(spuImages) != 1){
        logger.error("商品轮播图新增失败");
        throw new RuntimeException();
      }
    }
  }

  private void processSpuAttr(Spu spu) {
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
}
