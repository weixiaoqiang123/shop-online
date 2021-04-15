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
 * @date 2021/4/13 14:59
 */
@Data
@TableName("spu_attr")
@ApiModel("spu_attr")
public class SpuAttr {

  @TableId(value = "id",type = IdType.AUTO)
  private Integer id;

  @TableField("spu_code")
  private String spuCode;

  @TableField("属性编码")
  private Integer attrId;

  @TableField(exist = false)
  @ApiModelProperty("spu属性对应的属性值")
  private List<AttrValue> attrValueList;
}
