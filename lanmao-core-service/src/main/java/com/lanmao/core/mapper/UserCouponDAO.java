package com.lanmao.core.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lanmao.core.dataobject.UserCouponDO;

import java.util.List;

public interface UserCouponDAO extends BaseMapper<UserCouponDO> {

    List<UserCouponDO> selectList(UserCouponDO record);
}
