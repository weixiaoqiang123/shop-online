package com.wxq.business.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author weixiaoqiang
 * @date 2021/6/5 18:56
 */
@Configuration
public class FeignConfig {

  @Bean
  public Logger.Level feignLoggerLevel(){
    return Logger.Level.FULL;
  }
}
