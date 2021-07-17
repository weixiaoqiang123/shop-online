package com.wxq.goods.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wxq.bean.Spu;
import com.wxq.shopinterface.service.ISpuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 添加spu流程
 * => 新增商品基本信息(商品名称、商品描述、原价、优惠价、分类、封面图片)
 * => 新增商品详细信息(在spu_detail表中新增信息)
 * => 新增商品轮播图
 * => 新增商品属性和属性值
 * => 设置价格
 *
 * 1.新增基本信息保存后spu列表即会出现对应的信息
 * 2.点击修改可进入控制台修改配置
 * 3.新增sku时需判断SPU有无属性选项
 * 4.
 *  4.1 商家新增SKU后不能修改，如需为某个商品添加属性，可再次新增SKU，将原SKU下架
 *  4.1.1 已将该SKU加入购物车的用户下单该商品时会提示该商品已经下架(将订购按钮禁用，并将内容换为该商品已下架)
 *  4.1.2 已下单该商品的用户，继续发货
 *  4.1.3 SKU新增后不能修改和删除，只能查看
 * 5. SPU可修改的选项为
 *      上架前：spu_name、分类、商家分类、销售价格、封面图片
 *      上架后：商家分类、实际价格(上架后只能做折扣)
 *      下架：下架后不能再次上架
 * 6.不提供删除SPU和SKU的功能，交由定时任务做
 *
 *  可使用定时任务删除购物车和订单中 没有对下架spu和sku引用的spu、sku、属性、属性值进行删除(一周删除一次)
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
    String spuCode = spuService.add(spu);
    if(spuCode == null){
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }else {
      return new ResponseEntity<>(spuCode, HttpStatus.CREATED);
    }
  }

  /**
   * 修改spu时不能修改原价，只能通过开关设置折扣设置售价
   * @param spu
   * @return
   */
  @PutMapping
  public Object update(@RequestBody Spu spu){
    spuService.update(spu);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  // 不对外提供，交由定时任务
//  @DeleteMapping("/{id}")
//  public Object delete(@PathVariable("id") String spuCode){
//    spuService.delete(spuCode);
//    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//  }

  @GetMapping("/{id}")
  public Object get(@PathVariable("id") String spuCode){
    Spu spu = spuService.get(spuCode);
    return new ResponseEntity<>(spu, HttpStatus.OK);
  }

  @GetMapping
  public Object findByPage(@RequestParam Map<String, String> map,
                           @RequestParam("page") Integer page,
                           @RequestParam("size") Integer size){
    return null;
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
