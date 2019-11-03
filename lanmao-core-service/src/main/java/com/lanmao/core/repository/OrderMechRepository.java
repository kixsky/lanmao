package com.lanmao.core.repository;

import com.lanmao.common.base.BaseRepository;
import com.lanmao.common.utils.CommonUtils;
import com.lanmao.core.dataobject.OrderMechDO;
import com.lanmao.core.mapper.OrderMechDAO;
import com.lanmao.core.share.dto.OrderMechDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
@Slf4j
public class OrderMechRepository extends BaseRepository<OrderMechDTO> {

    @Resource
    private OrderMechDAO orderMechDAO;

    @Override
    public Long save(OrderMechDTO saveObject) {
        OrderMechDO record = new OrderMechDO();
        CommonUtils.copyProperties(saveObject, record);
        CommonUtils.setInsertDefaultValue(record);
        orderMechDAO.insert(record);
        return record.getId();
    }

    @Override
    public OrderMechDTO queryById(Long id) {
        return null;
    }

    @Override
    public List<OrderMechDTO> queryList(OrderMechDTO query) {
        return null;
    }

    @Override
    public int countQueryList(OrderMechDTO query) {
        return 0;
    }

    @Override
    public OrderMechDTO queryOne(OrderMechDTO query) {
        return null;
    }

    @Override
    public int updateById(OrderMechDTO updateObject) {
        return 0;
    }

    @Override
    public int deleteById(OrderMechDTO deleteObject) {
        return 0;
    }
}
