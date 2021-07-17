package com.wxq.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(scanBasePackages = {"com.wxq"})
@MapperScan("com.wxq.shopinterface.mapper")
@EnableDiscoveryClient
// 写了basePackages后远程调用类不用写@component注解
@EnableFeignClients(basePackages = "com.wxq.shopinterface.rpc")
@EnableSwagger2
public class ShopUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopUserApplication.class, args);
    }
}
