package com.wxq.goods.controller;

import com.alibaba.fastjson.JSONObject;
import com.qiniu.http.Response;
import com.wxq.goods.util.QiNiuYunUtil;
import com.wxq.util.common.ResponseVo;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author weixiaoqiang
 * @date 2021/4/19 17:21
 */
@RestController
@RequestMapping("/upload")
public class FileController {

  @Autowired
  private QiNiuYunUtil qiNiuYunUtil;

  @PostMapping
  public Object uploadImage(MultipartFile file){
    String originalFilename = file.getOriginalFilename();
    String type = FilenameUtils.getExtension(originalFilename);
    // 文件名称
    String finalFileName = System.currentTimeMillis()+"."+type;
    try {
      Response response = qiNiuYunUtil.upload(file, finalFileName);
      if(response.isOK() && response.isJson()){
        //获取图片在七牛云的地址
        Object key = JSONObject.parseObject(response.bodyString()).get("key");
        return new ResponseEntity<>(qiNiuYunUtil.getQiNiuYun().getQiniuImageDomain()+key, HttpStatus.OK);
      }
    } catch (IOException e) {
      e.printStackTrace();
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }
}
