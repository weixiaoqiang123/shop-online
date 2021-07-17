package com.wxq.goods.controller;

import com.wxq.bean.SpuDetail;
import com.wxq.shopinterface.service.ISpuDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author weixiaoqiang
 * @date 2021/6/11 16:47
 */
@RestController
@RequestMapping("/spu-detail")
public class SpuDetailController {

  @Autowired
  private ISpuDetailService spuDetailService;

  @PostMapping
  public Object add(@RequestBody SpuDetail spuDetail){
    spuDetailService.add(spuDetail);
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @PutMapping
  public Object update(@RequestBody SpuDetail spuDetail){
    spuDetailService.update(spuDetail);
    return new ResponseEntity<>(HttpStatus.OK);
  }
}
