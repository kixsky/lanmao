package com.lanmao.core.repository;

import com.lanmao.common.base.BaseRepository;
import com.lanmao.common.utils.CommonUtils;
import com.lanmao.core.dataobject.UserConsumeDO;
import com.lanmao.core.mapper.UserConsumeDAO;
import com.lanmao.core.share.dto.UserConsumeDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
@Slf4j
public class UserConsumeRepository extends BaseRepository<UserConsumeDTO> {

    @Resource
    private UserConsumeDAO userConsumeDAO;

    @Override
    public Long save(UserConsumeDTO saveObject) {
        CommonUtils.setInsertDefaultValue(saveObject);
        UserConsumeDO record = new UserConsumeDO();
        CommonUtils.copyProperties(saveObject, record);
        userConsumeDAO.insert(record);
        return record.getId();
    }

    @Override
    public UserConsumeDTO queryById(Long id) {
        return null;
    }

    @Override
    public List<UserConsumeDTO> queryList(UserConsumeDTO query) {
        return null;
    }

    @Override
    public UserConsumeDTO queryOne(UserConsumeDTO query) {
        return null;
    }

    @Override
    public int updateById(UserConsumeDTO updateObject) {
        return 0;
    }

    @Override
    public int deleteById(UserConsumeDTO deleteObject) {
        return 0;
    }
}
