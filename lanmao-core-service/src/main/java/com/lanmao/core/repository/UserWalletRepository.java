package com.lanmao.core.repository;

import com.lanmao.common.base.BaseRepository;
import com.lanmao.common.utils.CommonUtils;
import com.lanmao.core.dataobject.UserWalletDO;
import com.lanmao.core.mapper.UserWalletDAO;
import com.lanmao.core.share.dto.UserWalletDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
@Slf4j
public class UserWalletRepository extends BaseRepository<UserWalletDTO> {

    @Resource
    private UserWalletDAO userWalletDAO;

    @Override
    public Long save(UserWalletDTO saveObject) {
        CommonUtils.setInsertDefaultValue(saveObject);
        UserWalletDO record = new UserWalletDO();
        CommonUtils.copyProperties(saveObject, record);
        userWalletDAO.insert(record);
        return record.getId();
    }

    @Override
    public UserWalletDTO queryById(Long id) {
        return null;
    }

    @Override
    public List<UserWalletDTO> queryList(UserWalletDTO query) {
        return null;
    }

    @Override
    public int countQueryList(UserWalletDTO query) {
        return 0;
    }

    @Override
    public UserWalletDTO queryOne(UserWalletDTO query) {
        return null;
    }

    @Override
    public int updateById(UserWalletDTO updateObject) {
        return 0;
    }

    @Override
    public int deleteById(UserWalletDTO deleteObject) {
        return 0;
    }

    public UserWalletDTO getUserWallet(Long userId) {
        UserWalletDO query = new UserWalletDO();
        query.setUserId(userId);
        UserWalletDO record = userWalletDAO.selectOne(query);
        if (record != null) {
            UserWalletDTO userWalletDTO = new UserWalletDTO();
            CommonUtils.copyProperties(record, userWalletDTO);
            return userWalletDTO;
        }
        return null;
    }
}
