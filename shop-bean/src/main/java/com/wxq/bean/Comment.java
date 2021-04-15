package com.wxq.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author weixiaoqiang
 * @date 2021/4/13 11:28
 */
@Data
@ApiModel(value = "comment")
@TableName("comment")
public class Comment {

  @TableId(value = "id",type = IdType.AUTO)
  private Integer id;

  @TableField("business_code")
  @ApiModelProperty("评论商品的商家编码")
  private String businessCode;

  @TableField("sku_code")
  @ApiModelProperty("skuCode")
  private String skuCode;

  @TableField("comment_account")
  @ApiModelProperty("评论人账号")
  private String commentAccount;

  @TableField("reply_account")
  @ApiModelProperty("回复人账号")
  private String replyAccount;

  @TableField("content")
  @ApiModelProperty("回复内容")
  private String content;

  @TableField("comment_code")
  @ApiModelProperty("评论编码")
  private String commentCode;

  @TableField("comment_time")
  @ApiModelProperty("评论时间")
  private LocalDateTime commentTime;
}
