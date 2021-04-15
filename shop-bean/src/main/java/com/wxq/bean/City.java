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
@TableName("city")
@ApiModel("City")
public class City {

  @TableId(value = "id",type = IdType.AUTO)
  private Integer id;

  @TableField("name")
  @ApiModelProperty("市")
  private String name;

  @TableField("province_id")
  @ApiModelProperty("省id")
  private Integer provinceId;

  @TableField(exist = false)
  @ApiModelProperty("县")
  private List<County> countyList;
}
