package com.wxq.cate;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(scanBasePackages = {"com.wxq"})
@MapperScan("com.wxq.shopinterface.mapper")
@EnableDiscoveryClient
@EnableSwagger2
public class ShopCateApplication {

  public static void main(String[] args) {
    SpringApplication.run(ShopCateApplication.class, args);
  }
}
