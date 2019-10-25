package com.lanmao.core.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lanmao.core.dataobject.CouponDO;

import java.util.List;

public interface CouponDAO extends BaseMapper<CouponDO> {

    List<CouponDO> selectList(CouponDO record);

    int countSelectList(CouponDO record);
}
