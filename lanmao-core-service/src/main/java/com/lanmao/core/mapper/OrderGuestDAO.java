package com.lanmao.core.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lanmao.core.dataobject.OrderGuestDO;

import java.util.List;

public interface OrderGuestDAO extends BaseMapper<OrderGuestDO> {

    List<OrderGuestDO> selectList(OrderGuestDO record);

    int countSelectList(OrderGuestDO record);
}
