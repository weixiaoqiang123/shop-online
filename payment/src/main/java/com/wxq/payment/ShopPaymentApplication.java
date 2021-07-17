package com.wxq.payment;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author weixiaoqiang
 * @date 2021/4/23 11:09
 */
@SpringBootApplication
@EnableSwagger2
@EnableDiscoveryClient
@MapperScan("com.wxq.shopinterface.mapper")
public class ShopPaymentApplication {
  public static void main(String[] args) {
    SpringApplication.run(ShopPaymentApplication.class, args);
  }
}
