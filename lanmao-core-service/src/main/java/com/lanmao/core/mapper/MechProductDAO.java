package com.lanmao.core.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lanmao.core.dataobject.MechProductDO;
import com.lanmao.core.dataobject.ProductDO;

import java.util.List;

public interface MechProductDAO extends BaseMapper<MechProductDO> {

    List<MechProductDO> selectList(MechProductDO record);

    List<ProductDO> selectProduct(Long mechId);
}
