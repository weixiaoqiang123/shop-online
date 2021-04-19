package com.wxq.goods.controller;

import com.wxq.bean.Sku;
import com.wxq.shopinterface.service.ISkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/sku")
public class SkuController {

  @Autowired
  private ISkuService skuService;

  @PostMapping
  public Object add(@RequestBody Sku sku){
    skuService.add(sku);
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @PutMapping
  public Object update(@RequestBody Sku sku){
    skuService.update(sku);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public Object delete(@PathVariable("id") String skuCode){
    if(skuService.delete(skuCode)){
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  @GetMapping("/checkStore")
  public boolean checkStore(@RequestParam("skuCode") String skuCode,
                            @RequestParam("shopNum") Integer shopNum){
    Sku sku = skuService.get(skuCode);
    int store = sku.getStore();
    if(store < shopNum){
      return false;
    }
    return true;
  }

  /**
   * 下单修改库存接口
   * @param skuCode
   * @param shopNum
   * @return
   */
  @GetMapping("/updateStore")
  public boolean updateStore(@PathVariable("skuCode") String skuCode, @PathVariable("shopNum") Integer shopNum){
    Sku sku = skuService.get(skuCode);
    return skuService.updateStore(skuCode, sku.getStore() - shopNum);
  }

  /**
   * 管理界面修改库存接口
   */
  @GetMapping("/business/{id}")
  public Object businessUpdateStore(@PathVariable("id") String skuCode,
                                    @RequestParam("store") Integer store){
    if(skuService.updateStore(skuCode, store)){
      return new ResponseEntity<>(HttpStatus.OK);
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }
}
