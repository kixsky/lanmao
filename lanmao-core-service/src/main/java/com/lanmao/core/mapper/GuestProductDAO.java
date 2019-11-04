package com.lanmao.core.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lanmao.core.dataobject.GuestProductDO;

import java.util.List;

public interface GuestProductDAO extends BaseMapper<GuestProductDO> {

    List<GuestProductDO> selectList(GuestProductDO recprd);
}
