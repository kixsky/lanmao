package com.lanmao.core.repository;

import com.lanmao.common.base.BaseRepository;
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
        return null;
    }

    @Override
    public OrderGuestDTO queryById(Long id) {
        return null;
    }

    @Override
    public List<OrderGuestDTO> queryList(OrderGuestDTO query) {
        return null;
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
