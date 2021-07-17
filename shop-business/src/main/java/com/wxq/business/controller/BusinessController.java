package com.wxq.business.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wxq.bean.Business;
import com.wxq.shopinterface.service.IBusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/business")
public class BusinessController {

  @Autowired
  private IBusinessService businessService;

  @PostMapping
  public Object add(@RequestBody Business business){
    if(businessService.add(business)){
      return new ResponseEntity<>(HttpStatus.CREATED);
    }else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @PutMapping
  public Object update(@RequestBody Business business){
    if(businessService.update(business)){
      return new ResponseEntity<>(HttpStatus.OK);
    }else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/{id}")
  public Object delete(@PathVariable("id") String businessCode){
    if(businessService.delete(businessCode)){
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @GetMapping("/{id}")
  public Object get(@PathVariable("id") String businessCode){
    Business business = businessService.get(businessCode);
    return new ResponseEntity<>(business, HttpStatus.OK);
  }

  @GetMapping("/findBusinessByAccount")
  public Business findBusinessByAccount(@RequestParam String account){
    return businessService.findBusinessByAccount(account);
  }

  @GetMapping
  public Object findByPage(@RequestParam Map<String, String> business,
                           @RequestParam("currentPage") Integer currentPage,
                           @RequestParam("lineSize") Integer lineSize){
    Page<Business> businessList = businessService.findByPage(business, currentPage, lineSize);
    return new ResponseEntity<>(businessList, HttpStatus.OK);
  }
}
