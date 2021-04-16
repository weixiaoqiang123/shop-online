package com.wxq.cate.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.wxq.bean.Cate;
import com.wxq.shopinterface.mapper.CateMapper;
import com.wxq.shopinterface.service.ICateService;
import com.wxq.util.common.BaseTree;
import com.wxq.util.common.Page;
import com.wxq.util.common.TreeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CateServiceImpl implements ICateService {

  @Autowired
  private CateMapper cateMapper;

  @Override
  public boolean add(Cate cate) {
    return cateMapper.insert(cate) == 1;
  }

  @Override
  public boolean update(Cate cate) {
    UpdateWrapper<Cate> wrapper = new UpdateWrapper<>();
    wrapper.set("cate_code", cate.getCateCode());
    return cateMapper.update(cate, wrapper) == 1;
  }

  @Override
  public boolean delete(String cateCode) {
    UpdateWrapper<Cate> wrapper = new UpdateWrapper<>();
    wrapper.set("cate_code", cateCode);
    return cateMapper.delete(wrapper) == 1;
  }

  @Override
  public Cate findById(String cateCode) {
    QueryWrapper<Cate> wrapper = new QueryWrapper<>();
    wrapper.eq("cate_code", cateCode);
    return cateMapper.selectOne(wrapper);
  }

  @Override
  public List<BaseTree> findAll() {
    List<Cate> cates = cateMapper.findAll();
    BaseTree rootTree = new BaseTree();
    // 规定根节点树的id是0
    String rootNodeId = "0";
    TreeUtil.initTree(rootTree, cates, rootNodeId);
    return rootTree.getChildNodes();
  }

  @Override
  public Page<Cate> findByPage(Map<String, String> cate, Integer currentPage, Integer lineSize) {
    return null;
  }
}
