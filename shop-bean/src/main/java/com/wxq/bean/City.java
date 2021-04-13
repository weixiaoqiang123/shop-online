package com.wxq.bean;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author weixiaoqiang
 * @date 2021/4/13 9:40
 */
@Data
@TableName("city")
@ApiModel("City")
public class City {

  @TableField("id")
  private Integer id;

  @TableField("name")
  @ApiModelProperty("市")
  private String name;

  @TableField("province_id")
  @ApiModelProperty("省id")
  private Integer provinceId;

  @TableField(exist = false)
  @ApiModelProperty("县")
  private County county;
}
