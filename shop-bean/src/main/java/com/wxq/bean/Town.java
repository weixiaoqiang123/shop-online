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
@TableName("town")
@ApiModel(value = "Town", description = "")
public class Town {

  @TableField("id")
  private Integer id;

  @TableField("name")
  @ApiModelProperty("镇")
  private String name;

  @TableField("town_id")
  @ApiModelProperty("县id")
  private Integer countyId;
}
