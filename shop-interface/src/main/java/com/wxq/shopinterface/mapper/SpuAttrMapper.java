package com.wxq.shopinterface.mapper;


import com.wxq.bean.Attr;
import com.wxq.bean.SpuAttr;
import com.wxq.util.common.CommonMapper;
import org.apache.ibatis.annotations.Mapper;

/**AttrMapper.xml
 * @author weixiaoqiang
 * @date 2021/4/13 16:23
 */
@Mapper
public interface SpuAttrMapper extends CommonMapper<SpuAttr> {

  int count(String spuCode);
}
