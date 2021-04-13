package com.wxq.bean;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author weixiaoqiang
 * @date 2021/4/13 15:26
 */
@Data
@TableName("banner")
@ApiModel("系统轮播图")
public class Banner {

  @TableField("id")
  private Integer id;

  @TableField("path")
  @ApiModelProperty("图片路径")
  private String path;

  @TableField("status")
  @ApiModelProperty("图片状态: 0 禁用 1 启用")
  private Integer status;
}
