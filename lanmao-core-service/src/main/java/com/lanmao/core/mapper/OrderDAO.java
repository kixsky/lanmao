package com.lanmao.core.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lanmao.core.dataobject.OrderDO;

import java.util.List;

public interface OrderDAO extends BaseMapper<OrderDO> {

    List<OrderDO> selectList(OrderDO record);

    int countSelectList(OrderDO record);
}
