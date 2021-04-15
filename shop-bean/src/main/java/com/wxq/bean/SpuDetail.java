package com.wxq.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author weixiaoqiang
 * @date 2021/4/13 14:45
 */
@Data
@TableName
@ApiModel("商品详情信息，通过mavenEditor插件编辑")
public class SpuDetail {

  @TableId(value = "id",type = IdType.AUTO)
  private Integer id;

  @TableField("spu_code")
  @ApiModelProperty("spu编码")
  private String spuCode;

  @TableField("info")
  @ApiModelProperty("商品详情信息，将在插件中编辑的信息直接转成html进行存储")
  private String info;
}
