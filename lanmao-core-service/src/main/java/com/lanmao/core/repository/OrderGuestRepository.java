package com.lanmao.core.repository;

import com.lanmao.common.base.BaseRepository;
import com.lanmao.common.utils.CommonUtils;
import com.lanmao.core.dataobject.OrderGuestDO;
import com.lanmao.core.mapper.OrderGuestDAO;
import com.lanmao.core.share.dto.OrderGuestDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
@Slf4j
public class OrderGuestRepository extends BaseRepository<OrderGuestDTO> {

    @Resource
    private OrderGuestDAO orderGuestDAO;

    @Override
    public Long save(OrderGuestDTO saveObject) {
        OrderGuestDO record = new OrderGuestDO();
        CommonUtils.copyProperties(saveObject, record);
        CommonUtils.setInsertDefaultValue(record);
        orderGuestDAO.insert(record);
        return record.getId();
    }

    public List<OrderGuestDTO> getByOrderId(Long orderId) {
        OrderGuestDTO query = new OrderGuestDTO();
        query.setOrderId(orderId);
        return queryList(query);
    }

    @Override
    public OrderGuestDTO queryById(Long id) {
        return null;
    }

    @Override
    public List<OrderGuestDTO> queryList(OrderGuestDTO query) {
        OrderGuestDO record = new OrderGuestDO();
        CommonUtils.copyProperties(query, record);
        List<OrderGuestDO> list = orderGuestDAO.selectList(record);
        return CommonUtils.convertList(list, OrderGuestDTO.class);
    }

    @Override
    public int countQueryList(OrderGuestDTO query) {
        return 0;
    }

    @Override
    public OrderGuestDTO queryOne(OrderGuestDTO query) {
        return null;
    }

    @Override
    public int updateById(OrderGuestDTO updateObject) {
        return 0;
    }

    @Override
    public int deleteById(OrderGuestDTO deleteObject) {
        return 0;
    }
}
