package com.lanmao.core.repository;

import com.lanmao.common.base.BaseRepository;
import com.lanmao.common.utils.CommonUtils;
import com.lanmao.core.dataobject.OrderApplyDO;
import com.lanmao.core.mapper.OrderApplyDAO;
import com.lanmao.core.share.dto.OrderApplyDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
@Slf4j
public class OrderApplyRepository extends BaseRepository<OrderApplyDTO> {

    @Resource
    private OrderApplyDAO orderApplyDAO;

    @Override
    public Long save(OrderApplyDTO saveObject) {
        CommonUtils.setInsertDefaultValue(saveObject);
        OrderApplyDO record = new OrderApplyDO();
        CommonUtils.copyProperties(saveObject, record);
        orderApplyDAO.insert(record);
        return record.getId();
    }

    @Override
    public OrderApplyDTO queryById(Long id) {
        OrderApplyDO record = orderApplyDAO.selectById(id);
        OrderApplyDTO resultDTO = new OrderApplyDTO();
        CommonUtils.copyProperties(record, resultDTO);
        return resultDTO;
    }

    @Override
    public List<OrderApplyDTO> queryList(OrderApplyDTO query) {
        OrderApplyDO record = new OrderApplyDO();
        CommonUtils.copyProperties(query, record);
        List<OrderApplyDO> list = orderApplyDAO.selectList(record);
        return CommonUtils.convertList(list, OrderApplyDTO.class);
    }

    @Override
    public int countQueryList(OrderApplyDTO query) {
        OrderApplyDO record = new OrderApplyDO();
        CommonUtils.copyProperties(query, record);
        return orderApplyDAO.countSelectList(record);
    }

    @Override
    public OrderApplyDTO queryOne(OrderApplyDTO query) {
        List<OrderApplyDTO> list = queryList(query);
        if (CollectionUtils.isNotEmpty(list)) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public int updateById(OrderApplyDTO updateObject) {
        OrderApplyDO record = new OrderApplyDO();
        CommonUtils.copyProperties(updateObject, record);
        CommonUtils.setUpdateDefaultValue(record);
        return orderApplyDAO.updateById(record);
    }

    @Override
    public int deleteById(OrderApplyDTO deleteObject) {
        OrderApplyDO record = new OrderApplyDO();
        CommonUtils.copyProperties(deleteObject, record);
        CommonUtils.setDeleteDefaultValue(record);
        return orderApplyDAO.updateById(record);
    }
}
