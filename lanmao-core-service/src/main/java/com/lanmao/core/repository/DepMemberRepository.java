package com.lanmao.core.repository;

import com.lanmao.common.base.BaseRepository;
import com.lanmao.common.utils.CommonUtils;
import com.lanmao.core.dataobject.DepMemberDO;
import com.lanmao.core.mapper.DepMemberDAO;
import com.lanmao.core.share.dto.DepMemberDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
@Slf4j
public class DepMemberRepository extends BaseRepository<DepMemberDTO> {

    @Resource
    private DepMemberDAO depMemberDAO;

    @Override
    public Long save(DepMemberDTO saveObject) {
        return null;
    }

    @Override
    public DepMemberDTO queryById(Long id) {
        return null;
    }

    @Override
    public List<DepMemberDTO> queryList(DepMemberDTO query) {
        DepMemberDO record = new DepMemberDO();
        CommonUtils.copyProperties(query, record);
        List<DepMemberDO> list = depMemberDAO.selectList(record);
        return CommonUtils.convertList(list, DepMemberDTO.class);
    }

    @Override
    public DepMemberDTO queryOne(DepMemberDTO query) {
        List<DepMemberDTO> list = queryList(query);
        if (CollectionUtils.isNotEmpty(list)) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public int updateById(DepMemberDTO updateObject) {
        return 0;
    }

    @Override
    public int deleteById(DepMemberDTO deleteObject) {
        return 0;
    }
}
