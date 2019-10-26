package com.lanmao.core.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lanmao.core.dataobject.UserDO;

import java.util.List;

public interface UserDAO extends BaseMapper<UserDO> {

    List<UserDO> selectList(UserDO record);

    int countSelectList(UserDO record);
}
