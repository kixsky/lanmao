package com.lanmao.core.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lanmao.core.dataobject.UserWalletDO;

public interface UserWalletDAO extends BaseMapper<UserWalletDO> {

    UserWalletDO selectOne(UserWalletDO queryObject);
}
