package com.lanmao.core.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lanmao.core.dataobject.BrandDO;

import java.util.List;

public interface BrandDAO extends BaseMapper<BrandDO> {

    List<BrandDO> selectListByDO(BrandDO record);
}
