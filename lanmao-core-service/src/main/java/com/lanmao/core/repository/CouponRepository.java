package com.lanmao.core.repository;

import com.lanmao.common.base.BaseRepository;
import com.lanmao.common.utils.CommonUtils;
import com.lanmao.core.dataobject.CouponDO;
import com.lanmao.core.mapper.CouponDAO;
import com.lanmao.core.share.dto.CouponDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

@Repository
@Slf4j
public class CouponRepository extends BaseRepository<CouponDTO> {

    @Resource
    private CouponDAO couponDAO;

    @Override
    public Long save(@NotNull CouponDTO saveObject) {
        CouponDO record = new CouponDO();
        CommonUtils.copyProperties(saveObject, record);
        CommonUtils.setDefaultValue(record);
        couponDAO.insert(record);
        return record.getId();
    }

    @Override
    public CouponDTO queryById(Long id) {
        CouponDO record = couponDAO.selectById(id);
        if (record == null) {
            return null;
        }
        CouponDTO resultDTO = new CouponDTO();
        CommonUtils.copyProperties(record, resultDTO);
        return resultDTO;
    }

    @Override
    public List<CouponDTO> queryList(@NotNull CouponDTO query) {
        Map<String, Object> objMap = CommonUtils.toQueryMap(query);
        List<CouponDO> list = couponDAO.selectByMap(objMap);
        return CommonUtils.convertList(list, CouponDTO.class);
    }

    @Override
    public CouponDTO queryOne(@NotNull CouponDTO query) {
        List<CouponDTO> list = queryList(query);
        if (CollectionUtils.isNotEmpty(list)) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public int updateById(@NotNull CouponDTO updateObject) {
        CouponDO record = new CouponDO();
        CommonUtils.copyProperties(updateObject, record);
        return couponDAO.updateById(record);
    }
}
