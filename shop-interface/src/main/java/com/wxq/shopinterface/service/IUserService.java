package com.wxq.shopinterface.service;

import com.wxq.bean.User;
import com.wxq.util.common.Page;
import com.wxq.util.common.ResponseVo;

import java.util.Map;

public interface IUserService {

    ResponseVo<User> login(User user);

    ResponseVo<User> register(User user);

    boolean update(User user);

    void logout(String account, Integer role);

    boolean delete(String account, Integer role);

    User findById(String account, Integer role);

    Page<User> findByPage(Map<String, String> user, Integer currentPage, Integer lineSize);

    boolean checkLogin(String account, Integer role);
}
