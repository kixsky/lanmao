package com.lanmao.core.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lanmao.core.dataobject.MerchantDO;

import java.util.List;

public interface MerchantDAO extends BaseMapper<MerchantDO> {

    List<MerchantDO> selectListByDO(MerchantDO record);
}
