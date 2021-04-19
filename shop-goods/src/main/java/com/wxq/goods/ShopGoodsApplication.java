package com.wxq.goods;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@MapperScan("com.wxq.shopinterface.mapper")
@EnableDiscoveryClient
@EnableSwagger2
public class ShopGoodsApplication {

  public static void main(String[] args) {
    SpringApplication.run(ShopGoodsApplication.class,args);
  }
}
