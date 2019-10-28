package com.lanmao.core.repository;

import com.lanmao.common.base.BaseRepository;
import com.lanmao.common.utils.CommonUtils;
import com.lanmao.core.dataobject.OrderDO;
import com.lanmao.core.mapper.OrderDAO;
import com.lanmao.core.share.dto.OrderDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.List;

@Repository
@Slf4j
public class OrderRepository extends BaseRepository<OrderDTO> {

    @Resource
    private OrderDAO orderMapper;

    @Override
    public Long save(@NotNull OrderDTO orderDTO) {
        OrderDO record = new OrderDO();
        CommonUtils.copyProperties(orderDTO, record);
        CommonUtils.setInsertDefaultValue(record);
        orderMapper.insert(record);
        return record.getId();
    }

    @Override
    public OrderDTO queryById(Long id) {
        return null;
    }

    @Override
    public List<OrderDTO> queryList(OrderDTO query) {
        return null;
    }

    @Override
    public int countQueryList(OrderDTO query) {
        return 0;
    }

    @Override
    public OrderDTO queryOne(OrderDTO query) {
        return null;
    }

    @Override
    public int updateById(OrderDTO updateObject) {
        OrderDO record = new OrderDO();
        CommonUtils.copyProperties(updateObject, record);
        return orderMapper.updateById(record);
    }

    @Override
    public int deleteById(OrderDTO deleteObject) {
        return 0;
    }
}
