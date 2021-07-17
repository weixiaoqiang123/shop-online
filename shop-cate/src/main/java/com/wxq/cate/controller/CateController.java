package com.wxq.cate.controller;

import com.wxq.bean.Cate;
import com.wxq.shopinterface.service.ICateService;
import com.wxq.util.common.BaseTree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cate")
public class CateController {

  @Autowired
  private ICateService cateService;

  @PostMapping
  public Object addCate(@RequestBody Cate cate){
    if(cateService.add(cate)){
      return new ResponseEntity<>(HttpStatus.CREATED);
    }else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @PutMapping
  public Object update(@RequestBody Cate cate){
    if(cateService.update(cate)){
      return new ResponseEntity<>(HttpStatus.OK);
    }else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/{id}")
  public Object delete(@PathVariable("id") String cateCode){
    if(cateService.logicDelete(cateCode)){
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @GetMapping("/{cateCode}")
  public Object get(@PathVariable String cateCode){
    Cate cate = cateService.findById(cateCode);
    return new ResponseEntity<>(cate, HttpStatus.OK);
  }

  @GetMapping
  public Object listCate(){
    List<BaseTree> cateTree = cateService.findAll();
    return new ResponseEntity<>(cateTree, HttpStatus.OK);
  }

  /**
   * 查询最后一级分类
   * @targetDeep 目标深度，根据前端确定的深度指定
   * @return
   */
  @GetMapping("/findLastCate")
  public Object findLastCate(@RequestParam Integer targetDeep){
    List<BaseTree> cateList = cateService.findThirdCate(targetDeep);
    return new ResponseEntity<>(cateList, HttpStatus.OK);
  }
}
