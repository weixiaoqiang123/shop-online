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
@TableName("province")
@ApiModel("Province")
public class Province {

  @TableField("id")
  private Integer id;

  @TableField("name")
  @ApiModelProperty("省")
  private String name;

  @TableField(exist = false)
  @ApiModelProperty("市")
  private City city;
}
