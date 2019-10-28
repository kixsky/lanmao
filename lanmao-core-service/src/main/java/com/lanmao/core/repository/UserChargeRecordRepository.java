package com.lanmao.core.repository;

import com.lanmao.common.base.BaseRepository;
import com.lanmao.common.utils.CommonUtils;
import com.lanmao.core.dataobject.UserChargeRecordDO;
import com.lanmao.core.mapper.UserChargeRecordDAO;
import com.lanmao.core.share.dto.UserChargeRecordDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
@Slf4j
public class UserChargeRecordRepository extends BaseRepository<UserChargeRecordDTO> {

    @Resource
    private UserChargeRecordDAO userChargeRecordDAO;

    @Override
    public Long save(UserChargeRecordDTO saveObject) {
        CommonUtils.setInsertDefaultValue(saveObject);
        UserChargeRecordDO record = new UserChargeRecordDO();
        CommonUtils.copyProperties(saveObject, record);
        userChargeRecordDAO.insert(record);
        return record.getId();
    }

    @Override
    public UserChargeRecordDTO queryById(Long id) {
        UserChargeRecordDO recordDO = userChargeRecordDAO.selectById(id);
        UserChargeRecordDTO resultDTO = new UserChargeRecordDTO();
        CommonUtils.copyProperties(recordDO, resultDTO);
        return resultDTO;
    }

    @Override
    public List<UserChargeRecordDTO> queryList(UserChargeRecordDTO query) {
        return null;
    }

    @Override
    public int countQueryList(UserChargeRecordDTO query) {
        return 0;
    }

    @Override
    public UserChargeRecordDTO queryOne(UserChargeRecordDTO query) {
        return null;
    }

    @Override
    public int updateById(UserChargeRecordDTO updateObject) {
        return 0;
    }

    @Override
    public int deleteById(UserChargeRecordDTO deleteObject) {
        return 0;
    }
}
