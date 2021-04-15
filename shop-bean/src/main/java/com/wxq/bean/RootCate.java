package com.wxq.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author weixiaoqiang
 * @date 2021/4/13 15:22
 */
@Data
@TableName("root_cate")
@ApiModel("系统分类")
public class RootCate {

  @TableId(value = "id",type = IdType.AUTO)
  private Integer id;

  @TableField("cate_name")
  @ApiModelProperty("分类名称")
  private String cateName;

  @TableField("cate_code")
  @ApiModelProperty("分类编码")
  private String cateCode;

  @TableField(exist = false)
  @ApiModelProperty("一级分类")
  private List<Cate1> cate1List;
}
