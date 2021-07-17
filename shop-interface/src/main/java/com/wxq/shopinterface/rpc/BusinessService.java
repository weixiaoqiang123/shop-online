package com.wxq.shopinterface.rpc;

import com.wxq.bean.Business;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author weixiaoqiang
 * @date 2021/6/5 18:36
 */
//@Component
@FeignClient("shop-business")
public interface BusinessService {

  @GetMapping("/business/findBusinessByAccount")
  Business findBusinessByAccount(@RequestParam("account") String account);
}
