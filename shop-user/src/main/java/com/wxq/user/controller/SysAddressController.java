package com.wxq.user.controller;

import com.wxq.bean.Province;
import com.wxq.shopinterface.service.IProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author weixiaoqiang
 * @date 2021/4/20 14:24
 * 省、市、区/县、镇联动查询接口
 */
@RestController
@RequestMapping("/address")
public class SysAddressController {

  @Autowired
  private IProvinceService provinceService;

  @GetMapping
  public Object get(){
    List<Province> provinceList = provinceService.findAll();
    return new ResponseEntity<>(provinceList, HttpStatus.OK);
  }
}
