package com.wxq.order;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author weixiaoqiang
 * @date 2021/4/20 14:59
 */
@SpringBootApplication(scanBasePackages = {"com.wxq"})
@MapperScan("com.wxq.shopinterface.mapper")
@EnableDiscoveryClient
@EnableSwagger2
public class ShopOrderApplication {
  public static void main(String[] args) {
    SpringApplication.run(ShopOrderApplication.class, args);
  }
}
