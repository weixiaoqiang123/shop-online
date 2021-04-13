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
@TableName("county")
@ApiModel(value = "County", description = "")
public class County {

  @TableField("id")
  private Integer id;

  @TableField("name")
  @ApiModelProperty("县")
  private String name;

  @TableField("city_id")
  @ApiModelProperty("市id")
  private Integer cityId;

  @TableField(exist = false)
  @ApiModelProperty("镇/区")
  private Town town;
}
