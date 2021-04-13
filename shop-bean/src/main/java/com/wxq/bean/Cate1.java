package com.wxq.bean;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author weixiaoqiang
 * @date 2021/4/13 15:22
 */
@Data
@TableName("cate1")
@ApiModel("一级分类")
public class Cate1 {

  @TableField("id")
  private Integer id;

  @TableField("cate_name")
  @ApiModelProperty("分类名称")
  private String cateName;

  @TableField("cate_code")
  @ApiModelProperty("分类编码")
  private String cateCode;

  @TableField("parent_cate_code")
  @ApiModelProperty("父级分类编码")
  private String parentCateCode;

  @TableField(exist = false)
  @ApiModelProperty("二级分类")
  private Cate2 cate2;
}
