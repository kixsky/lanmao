package com.lanmao.core.repository;

import com.lanmao.common.base.BaseRepository;
import com.lanmao.common.utils.CommonUtils;
import com.lanmao.core.dataobject.UserCouponDO;
import com.lanmao.core.mapper.UserCouponDAO;
import com.lanmao.core.share.dto.UserCouponDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
@Slf4j
public class UserCouponRepository extends BaseRepository<UserCouponDTO> {

    @Resource
    private UserCouponDAO userCouponDAO;

    @Override
    public Long save(UserCouponDTO saveObject) {
        CommonUtils.setInsertDefaultValue(saveObject);
        UserCouponDO record = new UserCouponDO();
        CommonUtils.copyProperties(saveObject, record);
        userCouponDAO.insert(record);
        return record.getId();
    }

    @Override
    public UserCouponDTO queryById(Long id) {
        return null;
    }

    @Override
    public List<UserCouponDTO> queryList(UserCouponDTO query) {
        return null;
    }

    @Override
    public int countQueryList(UserCouponDTO query) {
        return 0;
    }

    @Override
    public UserCouponDTO queryOne(UserCouponDTO query) {
        return null;
    }

    @Override
    public int updateById(UserCouponDTO updateObject) {
        return 0;
    }

    @Override
    public int deleteById(UserCouponDTO deleteObject) {
        return 0;
    }
}
