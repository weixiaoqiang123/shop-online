package com.wxq.cate.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wxq.bean.Cate;
import com.wxq.shopinterface.mapper.CateMapper;
import com.wxq.shopinterface.service.ICateService;
import com.wxq.util.common.BaseTree;
import com.wxq.util.common.StringUtil;
import com.wxq.util.common.TreeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CateServiceImpl implements ICateService {

  @Autowired
  private CateMapper cateMapper;

  @Override
  public boolean add(Cate cate) {
    // 生成分类编码
    String cateCode = StringUtil.getCode();
    cate.setCateCode(cateCode);
    cate.setIsDelete(0);
    return cateMapper.insert(cate) == 1;
  }

  @Override
  public boolean update(Cate cate) {
    return cateMapper.updateByCondition("cate",
    Wrappers.update().set("cate_name", cate.getCateName()).eq("cate_code", cate.getCateCode())) == 1;
  }

  @Override
  public boolean logicDelete(String cateCode) {
    return cateMapper.updateByCondition("cate", Wrappers.update()
    .set("is_delete", 1).eq("cate_code", cateCode)) == 1;
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

  @Override
  public List<BaseTree> findThirdCate(Integer targetDeep) {
    List<BaseTree> treeList = this.findAll();
    return treeList.stream().filter(tree -> tree.getDeep().equals(targetDeep)).collect(Collectors.toList());
  }
}
