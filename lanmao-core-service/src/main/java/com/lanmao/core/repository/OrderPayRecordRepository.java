package com.lanmao.core.repository;

import com.lanmao.common.base.BaseRepository;
import com.lanmao.core.mapper.OrderPayRecordDAO;
import com.lanmao.core.share.dto.OrderPayRecordDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
@Slf4j
public class OrderPayRecordRepository extends BaseRepository<OrderPayRecordDTO> {

    @Resource
    private OrderPayRecordDAO orderPayRecordDAO;

    @Override
    public Long save(OrderPayRecordDTO saveObject) {
        return null;
    }

    @Override
    public OrderPayRecordDTO queryById(Long id) {
        return null;
    }

    @Override
    public List<OrderPayRecordDTO> queryList(OrderPayRecordDTO query) {
        return null;
    }

    @Override
    public int countQueryList(OrderPayRecordDTO query) {
        return 0;
    }

    @Override
    public OrderPayRecordDTO queryOne(OrderPayRecordDTO query) {
        return null;
    }

    @Override
    public int updateById(OrderPayRecordDTO updateObject) {
        return 0;
    }

    @Override
    public int deleteById(OrderPayRecordDTO deleteObject) {
        return 0;
    }
}
