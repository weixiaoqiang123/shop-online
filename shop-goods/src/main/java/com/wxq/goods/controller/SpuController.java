package com.wxq.goods.controller;

import com.wxq.bean.Spu;
import com.wxq.shopinterface.service.ISpuService;
import com.wxq.util.common.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * 添加spu流程
 * => 新增商品基本信息(商品名称、商品描述、原价、优惠价、分类、封面图片)
 * => 新增商品详细信息(在spu_detail表中新增信息)
 * => 新增商品属性和属性值
 *
 * spu只能添加和删除，无法修改属性和属性值
 * 添加时可以任意添加、修改、删除属性属性值
 */
@RestController
@RequestMapping("/spu")
public class SpuController {

  @Autowired
  private ISpuService spuService;

  /**
   * 添加spu
   * 添加spu时只能设置原价
   * @param spu
   * @return
   */
  @PostMapping
  public Object add(@RequestBody Spu spu){
    spuService.add(spu);
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  /**
   * 修改spu时不能修改原价，只能通过开关设置折扣设置售价
   * @param spu
   * @return
   */
  @PutMapping
  public Object update(@RequestBody Spu spu){
    spuService.add(spu);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public Object delete(@PathVariable("id") String spuCode){
    spuService.delete(spuCode);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @GetMapping("/{id}")
  public Object get(@PathVariable("id") String spuCode){
    Spu spu = spuService.get(spuCode);
    return new ResponseEntity<>(spu, HttpStatus.OK);
  }

  @GetMapping("/updateSpuStatus")
  public Object updateSpuStatus(@RequestParam("spuCode")String spuCode, @RequestParam("status") Integer staus){
    if(spuService.updateSpuStatus(spuCode, staus)){
      return new ResponseEntity<>(HttpStatus.OK);
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  /**
   * 商家管理商品接口
   * business_cate_code==null 查询该商家下的所有商品
   */
  @GetMapping("/business/{id}")
  public Object findByBusinessCateCode(@PathVariable("id") String businessCode,
                                       @RequestParam("businessCateCode") String businessCateCode,
                                       @RequestParam("currentPage") Integer currentPage,
                                       @RequestParam("lineSize") Integer lineSize){
    Page<Spu> spuList = spuService.findByBusinessCateCode(businessCode, businessCateCode, currentPage, lineSize);
    return new ResponseEntity<>(spuList, HttpStatus.OK);
  }
}
