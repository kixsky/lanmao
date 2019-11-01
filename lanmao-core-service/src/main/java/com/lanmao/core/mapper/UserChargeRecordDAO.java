package com.lanmao.core.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lanmao.core.dataobject.UserChargeRecordDO;

import java.util.List;

public interface UserChargeRecordDAO extends BaseMapper<UserChargeRecordDO> {

    List<UserChargeRecordDO> selectList(UserChargeRecordDO record);
}
