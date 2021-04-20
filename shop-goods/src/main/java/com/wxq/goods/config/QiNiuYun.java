package com.wxq.goods.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "qiniu")
public class QiNiuYun {

  String accessKey;
  String secretKey;
  String bucketName;
  String qiniuImageDomain;

  public String getAccessKey() {
    return accessKey;
  }

  public void setAccessKey(String accessKey) {
    this.accessKey = accessKey;
  }

  public String getSecretKey() {
    return secretKey;
  }

  public void setSecretKey(String secretKey) {
    this.secretKey = secretKey;
  }

  public String getBucketName() {
    return bucketName;
  }

  public void setBucketName(String bucketName) {
    this.bucketName = bucketName;
  }

  public String getQiniuImageDomain() {
    return qiniuImageDomain;
  }

  public void setQiniuImageDomain(String qiniuImageDomain) {
    this.qiniuImageDomain = qiniuImageDomain;
  }
}
