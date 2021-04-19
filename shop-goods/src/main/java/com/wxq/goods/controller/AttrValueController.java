package com.wxq.goods.controller;

import com.wxq.bean.AttrValue;
import com.wxq.shopinterface.service.IAttrValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/attr-value")
public class AttrValueController {

  @Autowired
  private IAttrValueService attrValueService;

  @PostMapping
  public Object add(@RequestBody AttrValue attrValue){
    if(attrValueService.add(attrValue)){
      return new ResponseEntity<>(HttpStatus.CREATED);
    }else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @PutMapping
  public Object update(@RequestBody AttrValue attrValue){
    if(attrValueService.update(attrValue)){
      return new ResponseEntity<>(HttpStatus.OK);
    }else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/{id}")
  public Object delete(@PathVariable Integer id){
    if(attrValueService.delete(id)){
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }
}
