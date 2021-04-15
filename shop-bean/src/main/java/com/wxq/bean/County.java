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
 * @date 2021/4/13 9:40
 */
@Data
@TableName("county")
@ApiModel(value = "County", description = "")
public class County {

  @TableId(value = "id",type = IdType.AUTO)
  private Integer id;

  @TableField("name")
  @ApiModelProperty("县")
  private String name;

  @TableField("city_id")
  @ApiModelProperty("市id")
  private Integer cityId;

  @TableField(exist = false)
  @ApiModelProperty("镇/区")
  private List<Town> townList;
}
