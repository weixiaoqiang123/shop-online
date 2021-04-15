package com.wxq.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author weixiaoqiang
 * @date 2021/4/13 14:56
 */
@Data
@TableName("attr_value")
@ApiModel("属性值")
public class AttrValue {

  @TableId(value = "id",type = IdType.AUTO)
  private Integer id;

  @TableField("name")
  @ApiModelProperty("属性值名称")
  private String name;

  @TableField("attr_id")
  @ApiModelProperty("属性id")
  private Integer attrId;

  @TableField("is_delete")
  @ApiModelProperty("是否删除: 0 未删除 1 已删除")
  private Integer isDelete;
}
