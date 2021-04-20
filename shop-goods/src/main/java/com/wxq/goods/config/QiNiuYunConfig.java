package com.wxq.goods.config;

import com.qiniu.common.Zone;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 七牛云图片服务器配置类
 */
@Configuration
public class QiNiuYunConfig {

  @Autowired
  private QiNiuYun qiNiuYun;
  /**
   * 密钥配置
   */
  @Bean
  public Auth getAuth(){
    return Auth.create(qiNiuYun.getAccessKey(), qiNiuYun.getSecretKey());
  }

  @Bean
  public UploadManager getUploadManager(){
    /**
     * 构造一个带指定Zone对象的配置类,不同的七云牛存储区域调用不同的zone
     */
    com.qiniu.storage.Configuration cfg = new com.qiniu.storage.Configuration(Zone.zone2());
    return new UploadManager(cfg);
  }
}
