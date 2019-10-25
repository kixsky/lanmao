package com.lanmao.core.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lanmao.core.dataobject.MechDO;

import java.util.List;

public interface MechDAO extends BaseMapper<MechDO> {

    List<MechDO> selectList(MechDO record);

    int countSelectList(MechDO record);
}
