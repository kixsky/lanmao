package com.lanmao.core.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lanmao.core.dataobject.ShopDO;

import java.util.List;

public interface ShopDAO extends BaseMapper<ShopDO> {

    List<ShopDO> selectListByDO(ShopDO record);
}
