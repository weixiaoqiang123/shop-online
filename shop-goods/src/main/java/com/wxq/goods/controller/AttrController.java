package com.wxq.goods.controller;

import com.wxq.bean.Attr;
import com.wxq.shopinterface.service.IAttrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/attr")
public class AttrController {

  @Autowired
  private IAttrService attrService;

  @PostMapping
  public Object add(@RequestBody Attr attr){
    if(attrService.add(attr)){
      return new ResponseEntity<>(HttpStatus.CREATED);
    }else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @PutMapping
  public Object update(@RequestBody Attr attr){
    if(attrService.update(attr)){
      return new ResponseEntity<>(HttpStatus.OK);
    }else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/{id}")
  public Object delete(@PathVariable Integer id){
    if(attrService.delete(id)){
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }
}
