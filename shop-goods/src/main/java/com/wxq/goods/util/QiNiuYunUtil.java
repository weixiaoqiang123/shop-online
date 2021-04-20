package com.wxq.goods.util;

import com.qiniu.http.Response;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.wxq.goods.config.QiNiuYun;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Component
public class QiNiuYunUtil {

  @Autowired
  private UploadManager uploadManager;

  @Autowired
  private Auth auth;

  @Autowired
  private QiNiuYun qiNiuYun;

  public Response upload(MultipartFile file, String fileName) throws IOException {
    return uploadManager.put(file.getBytes(), fileName, auth.uploadToken(qiNiuYun.getBucketName()));
  }

  public QiNiuYun getQiNiuYun(){
    return qiNiuYun;
  }
}
