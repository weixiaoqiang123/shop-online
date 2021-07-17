package com.wxq.user.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wxq.bean.User;
import com.wxq.shopinterface.service.IUserService;
import com.wxq.util.common.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

  @Autowired
  private IUserService userService;

  @PostMapping("/login")
  public ResponseVo<User> login(@RequestBody User user){
    return userService.login(user);
  }

  @PostMapping
  public ResponseVo<User> register(@RequestBody User user){
    return userService.register(user);
  }

  @PutMapping
  public Object update(@RequestBody User user){
    if(userService.update(user)){
      return new ResponseEntity<>(HttpStatus.OK);
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  @GetMapping("/{id}")
  public Object logout(@PathVariable("id") String account, @RequestParam("role") Integer role) {
    userService.logout(account, role);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @DeleteMapping("/{account}")
  public Object delete(@PathVariable String account,@RequestParam("role") Integer role){
    if(userService.delete(account, role)){
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  @GetMapping("/findById")
  public Object findById(@RequestParam("account") String account, @RequestParam("role") Integer role){
    User user = userService.findById(account, role);
    return new ResponseEntity<>(user, HttpStatus.OK);
  }

  @GetMapping("/findByPage")
  public Page<User> findByPage(@RequestParam Map<String, String> user,
                               @RequestParam("currentPage") Integer currentPage,
                               @RequestParam("lineSize") Integer lineSize){
    Page<User> page = userService.findByPage(user, currentPage, lineSize);
    return page;
  }

  @GetMapping("/checkLogin")
  public boolean checkLogin(@RequestParam("account") String account, @RequestParam("role") Integer role){
    return userService.checkLogin(account, role);
  }
}
