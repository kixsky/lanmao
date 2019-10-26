package com.lanmao.core.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lanmao.core.dataobject.DepMemberDO;

import java.util.List;

public interface DepMemberDAO extends BaseMapper<DepMemberDO> {

    List<DepMemberDO> selectList(DepMemberDO record);
}
