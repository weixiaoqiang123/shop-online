package com.wxq.user.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wxq.bean.Business;
import com.wxq.bean.User;
import com.wxq.shopinterface.mapper.UserMapper;
import com.wxq.shopinterface.rpc.BusinessService;
import com.wxq.shopinterface.service.IUserService;
import com.wxq.util.common.*;
import jdk.nashorn.internal.ir.annotations.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import java.math.BigDecimal;
import java.util.Map;

@Service
public class UserServiceImpl implements IUserService {

  @Autowired
  private UserMapper userMapper;

  @Autowired
  private RedisUtil redisUtil;

  @Autowired
  private BusinessService bussinessService;

  @Override
  public ResponseVo<User> login(User user) {
    ResponseVo<User> responseVo = new ResponseVo<>(false, ErrorCode.FAIL, "登录失败");
    QueryWrapper<User> wrapper = new QueryWrapper<>();
    wrapper.eq("account", user.getAccount());
    wrapper.eq("is_delete", 0);
    User userByDb = userMapper.selectOne(wrapper);
    if(userByDb == null){
      responseVo.setMsg("用户不存在");
    }else {
      String password = userByDb.getPassword();
      if(user.getPassword().equals(password)){
        // 如果是商家查询商家信息
        if(userByDb.getRole() == 1){
          Business business = bussinessService.findBusinessByAccount(user.getAccount());
          userByDb.setBusiness(business);
        }
        responseVo.setInstance(true, ErrorCode.SUCCESS, "登录成功", userByDb);
        // 获取jedis
        Jedis jedis = redisUtil.getJedis();
        // 放入redis ,必须起key=user:userId:info
        String userKey = ConstUtil.getKey(user.getAccount(), userByDb.getRole());
        // 保存数据
        jedis.setex(userKey, ConstUtil.KEY_TIME_OUT, JSON.toJSONString(userByDb));
        // 关闭资源
        jedis.close();
      }else {
        responseVo.setMsg("密码错误");
      }
    }
    return responseVo;
  }

  /**
   * 1. 初始化账号余额
   * 2. 校验昵称是否重复
   * 3. 校验账号是否注册
   *    a. 未注册 新增
   *    b. 注册 判断is_delete是否为0
   *      (1) 为0 该账号已经注册
   *      (2) 不为0 账号已经注销，修改用户信息并将is_delete修改为0
   * @param user 用户信息
   * @return
   */
  @Override
  public ResponseVo<User> register(User user) {
    ResponseVo<User> responseVo = new ResponseVo<>(false, ErrorCode.FAIL, "注册失败");
    user.setBalance(new BigDecimal(0));
    String account = user.getAccount();
    String nickname = user.getNickname();
    Integer role = user.getRole();
    QueryWrapper<User> wrapper = new QueryWrapper<>();
    wrapper.eq("nickname", nickname);
    wrapper.eq("role", role);
    boolean flag = false;
    if(userMapper.selectOne(wrapper) != null){
      responseVo.setMsg("昵称重复");
    }else {
      QueryWrapper<User> wrapper1 = new QueryWrapper<>();
      wrapper1.eq("account", account);
      wrapper1.eq("role", role);
      User userByDb = userMapper.selectOne(wrapper1);
      if(userByDb == null){
        flag = userMapper.insert(user) == 1;
      }else {
        Integer isDelete = userByDb.getIsDelete();
        if(isDelete == 0){
          responseVo.setMsg("账号已被注册");
        }else {
          user.setIsDelete(0);
          UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
          updateWrapper.eq("account", account);
          updateWrapper.eq("is_delete", isDelete);
          updateWrapper.eq("role", role);
          flag = userMapper.update(user, updateWrapper) == 1;
        }
      }
    }

    if(flag){
      responseVo.setInstance(true, ErrorCode.SUCCESS, "注册成功", null);
      return responseVo;
    }
    return responseVo;
  }

  @Override
  public boolean update(User user) {
    return userMapper.updateByCondition("t_sys_user",Wrappers.update()
        .set("nickname", user.getNickname())
        .set("name", user.getName())
        .set("head_img", user.getHeadImg())
        .eq("account", user.getAccount())) == 1;
  }

  @Override
  public void logout(String account, Integer role) {
    Jedis jedis = redisUtil.getJedis();
    String userKey = ConstUtil.getKey(account, role);
    jedis.del(userKey);
    jedis.close();
  }

  @Override
  public boolean delete(String account, Integer role) {
    UpdateWrapper<User> wrapper = new UpdateWrapper<>();
    wrapper.eq("account", account);
    wrapper.eq("role", role);
    return userMapper.updateByCondition("user", Wrappers.update()
        .set("is_delete", 1)
        .eq("account", account)
        .eq("role", role)) == 1;
  }

  @Override
  public User findById(String account, Integer role) {
    QueryWrapper<User> wrapper = new QueryWrapper<>();
    wrapper.eq("account", account);
    wrapper.eq("role", role);
    wrapper.eq("is_delete", 0);
    return userMapper.selectOne(wrapper);
  }

  @Override
  public Page<User> findByPage(Map<String, String> user, Integer currentPage, Integer lineSize) {
    QueryWrapper<User> wrapper = new QueryWrapper<>();
    wrapper.eq("role", user.get("role"));
    wrapper.like("nickname", user.get("nickname"));
    wrapper.like("name", user.get("name"));
//    String method = user.get("method");
//    if(!method.isEmpty()){
//      wrapper.eq("method", method);
//    }
    return PageUtil.getData(userMapper, wrapper, currentPage, lineSize);
  }

  @Override
  public boolean checkLogin(String account, Integer role) {
    Jedis jedis = redisUtil.getJedis();
    String userJson = jedis.get(ConstUtil.getKey(account, role));
    return userJson != null;
  }
}
