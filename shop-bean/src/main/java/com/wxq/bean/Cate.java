package com.wxq.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.wxq.util.common.ModelInterface;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author weixiaoqiang
 * @date 2021/4/13 15:22
 */
@TableName("cate")
@ApiModel("系统分类")
public class Cate implements ModelInterface {

  @TableId(value = "id",type = IdType.AUTO)
  private Integer id;

  @TableField("cate_name")
  @ApiModelProperty("分类名称")
  private String cateName;

  @TableField("cate_code")
  @ApiModelProperty("分类编码")
  private String cateCode;

  @TableField("parent_cate_code")
  @ApiModelProperty("父级分类编码")
  private String parentCateCode;

  @TableField("is_delete")
  @ApiModelProperty("是否删除: 0 未删除 1 删除")
  private Integer isDelete;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getCateName() {
    return cateName;
  }

  public void setCateName(String cateName) {
    this.cateName = cateName;
  }

  public String getCateCode() {
    return cateCode;
  }

  public void setCateCode(String cateCode) {
    this.cateCode = cateCode;
  }

  public String getParentCateCode() {
    return parentCateCode;
  }

  public void setParentCateCode(String parentCateCode) {
    this.parentCateCode = parentCateCode;
  }

  public Integer getIsDelete() {
    return isDelete;
  }

  public void setIsDelete(Integer isDelete) {
    this.isDelete = isDelete;
  }

  @Override
  public String getName() {
    return this.cateName;
  }

  @Override
  public String getCode() {
    return this.cateCode;
  }

  @Override
  public String getParentCode() {
    return this.parentCateCode;
  }
}
