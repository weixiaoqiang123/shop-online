package com.wxq.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;

/**
 * @author weixiaoqiang
 * @date 2021/4/13 14:49
 */
@Data
@TableName("spu_images")
@ApiModel(value = "商品图片表")
public class SpuImages {

  @TableId(value = "id",type = IdType.AUTO)
  private Integer id;

  @TableField("spu_code")
  @ApiModelProperty("spu编码")
  private String spuCode;

  @TableField("imagePath")
  @ApiModelProperty("商品详情图片路径")
  private String imagePath;
}
