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
 * @date 2021/4/13 14:53
 */
@Data
@TableName("attr")
@ApiModel("attr")
public class Attr {

  @TableId(value = "id",type = IdType.AUTO)
  private Integer id;

  @TableField("name")
  @ApiModelProperty("属性名称")
  private String name;

  @TableField("is_delete")
  @ApiModelProperty("是否删除: 0 未删除 1 已删除")
  private Integer isDelete;

  @TableField(exist = false)
  @ApiModelProperty("属性对应的属性值")
  private List<AttrValue> attrValueList;
}
