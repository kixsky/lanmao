package com.lanmao.core.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lanmao.core.dataobject.OrderApplyDO;

import java.util.List;

public interface OrderApplyDAO extends BaseMapper<OrderApplyDO> {

    List<OrderApplyDO> selectList(OrderApplyDO record);

    int countSelectList(OrderApplyDO record);
}
