package com.lanmao.core.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lanmao.core.dataobject.UserWalletDO;

import java.util.List;

public interface UserWalletDAO extends BaseMapper<UserWalletDO> {

    UserWalletDO selectOne(UserWalletDO queryObject);

    List<UserWalletDO> selectList(UserWalletDO reocrd);
}
