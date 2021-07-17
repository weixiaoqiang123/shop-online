package com.wxq.util;

import lombok.Data;

import java.util.List;

/**
 * @author weixiaoqiang
 * @date 2021/4/22 17:20
 */
@Data
public class OrderPreVo {

  private String account;

  private List<GoodsVo> goodsVoList;
}
