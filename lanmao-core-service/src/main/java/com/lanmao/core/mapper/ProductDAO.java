package com.lanmao.core.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lanmao.core.dataobject.ProductDO;

import java.util.List;

public interface ProductDAO extends BaseMapper<ProductDO> {

    List<ProductDO> selectList(ProductDO record);

    int countSelectList(ProductDO record);
}
