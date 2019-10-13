package com.lanmao.core.repository;

import com.lanmao.common.base.BaseRepository;
import com.lanmao.core.dataobject.DepMemberDO;
import com.lanmao.core.mapper.DepMemberDAO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
@Slf4j
public class DepMemberRepository extends BaseRepository<DepMemberDO> {

    @Resource
    private DepMemberDAO depMemberDAO;

    @Override
    public Long save(DepMemberDO saveObject) {
        return null;
    }

    @Override
    public DepMemberDO queryById(Long id) {
        return null;
    }

    @Override
    public List<DepMemberDO> queryList(DepMemberDO query) {
        return null;
    }

    @Override
    public DepMemberDO queryOne(DepMemberDO query) {
        return null;
    }

    @Override
    public int updateById(DepMemberDO updateObject) {
        return 0;
    }
}
