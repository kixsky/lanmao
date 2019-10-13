package com.lanmao.core.repository;

import com.lanmao.common.base.BaseRepository;
import com.lanmao.common.constants.YesOrNoEnum;
import com.lanmao.common.utils.CommonUtils;
import com.lanmao.core.dataobject.UserDO;
import com.lanmao.core.mapper.UserDAO;
import com.lanmao.core.share.dto.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Repository
@Slf4j
public class UserRepository implements BaseRepository<UserDTO> {

    @Resource
    private UserDAO userDAO;

    @Override
    public Long save(UserDTO saveObject) {
        return null;
    }

    @Override
    public UserDTO queryById(Long id) {
        return null;
    }

    @Override
    public List<UserDTO> queryList(UserDTO query) {
        query.setIsDeleted(YesOrNoEnum.NO.getCode());
        Map<String, Object> objMap = CommonUtils.toQueryMap(query);
        List<UserDO> list = userDAO.selectByMap(objMap);
        return CommonUtils.convertList(list, UserDTO.class);
    }

    @Override
    public UserDTO queryOne(UserDTO query) {
        return null;
    }

    @Override
    public int updateById(UserDTO updateObject) {
        return 0;
    }
}
