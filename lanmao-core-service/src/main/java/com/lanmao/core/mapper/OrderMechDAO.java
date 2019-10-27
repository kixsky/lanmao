package com.lanmao.core.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lanmao.core.dataobject.OrderMechDO;

import java.util.List;

public interface OrderMechDAO extends BaseMapper<OrderMechDO> {

    List<OrderMechDO> selectList(OrderMechDO record);

    int countSelectList(OrderMechDO record);
}
