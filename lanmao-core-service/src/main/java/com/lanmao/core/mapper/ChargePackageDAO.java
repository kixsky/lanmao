package com.lanmao.core.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lanmao.core.dataobject.ChargePackageDO;

import java.util.List;

public interface ChargePackageDAO extends BaseMapper<ChargePackageDO> {


    List<ChargePackageDO> selectList(ChargePackageDO record);
}
