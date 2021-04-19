package com.wxq.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author weixiaoqiang
 * @date 2021/4/13 15:05
 */
@TableName("su")
@ApiModel("sku")
public class Sku {

  @TableId(value = "id",type = IdType.AUTO)
  private Integer id;

  @TableField("price")
  @ApiModelProperty("每种sku的实际价格")
  private BigDecimal price;

  @TableField("sku_code")
  @ApiModelProperty("sku编码")
  private String skuCode;

  @TableField("spu_code")
  @ApiModelProperty("spu编码")
  private String spuCode;

  @TableField("store")
  @ApiModelProperty("库存")
  private Integer store;

  @TableField("image_path")
  @ApiModelProperty("选择参数时展示的商品图片的图片路径")
  private String imagePath;

  @TableField("is_delete")
  @ApiModelProperty("是否删除")
  private Integer isDelete;

  @TableField(exist = false)
  @ApiModelProperty("减少库存操作使用该字段")
  private AtomicInteger realStore;

  @TableField(exist = false)
  @ApiModelProperty("新增sku时直接传入该属性")
  private Spu spu;

  @TableField(exist = false)
  @ApiModelProperty("前端传入sku的价格")
  private String fontPrice;

  @TableField(exist = false)
  @ApiModelProperty("sku拥有的属性和属性值")
  private List<SkuAttrValue> skuAttrValueList;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  public String getSkuCode() {
    return skuCode;
  }

  public void setSkuCode(String skuCode) {
    this.skuCode = skuCode;
  }

  public String getSpuCode() {
    return spuCode;
  }

  public void setSpuCode(String spuCode) {
    this.spuCode = spuCode;
  }

  public Integer getStore() {
    return store;
  }

  public void setStore(Integer store) {
    this.store = store;
  }

  public String getImagePath() {
    return imagePath;
  }

  public void setImagePath(String imagePath) {
    this.imagePath = imagePath;
  }

  public Integer getIsDelete() {
    return isDelete;
  }

  public void setIsDelete(Integer isDelete) {
    this.isDelete = isDelete;
  }

  public AtomicInteger getRealStore() {
    return new AtomicInteger(this.store);
  }

  public void setRealStore(AtomicInteger realStore) {
    this.store = realStore.get();
  }

  public Spu getSpu() {
    return spu;
  }

  public void setSpu(Spu spu) {
    this.spu = spu;
  }

  public String getFontPrice() {
    return fontPrice;
  }

  public void setFontPrice(String fontPrice) {
    this.fontPrice = fontPrice;
  }

  public List<SkuAttrValue> getSkuAttrValueList() {
    return skuAttrValueList;
  }

  public void setSkuAttrValueList(List<SkuAttrValue> skuAttrValueList) {
    this.skuAttrValueList = skuAttrValueList;
  }
}
