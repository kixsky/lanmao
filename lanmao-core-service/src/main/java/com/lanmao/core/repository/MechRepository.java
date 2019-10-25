package com.lanmao.core.repository;

import com.lanmao.common.base.BaseRepository;
import com.lanmao.common.utils.CommonUtils;
import com.lanmao.core.dataobject.MechDO;
import com.lanmao.core.mapper.MechDAO;
import com.lanmao.core.share.dto.MechDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
@Slf4j
public class MechRepository extends BaseRepository<MechDTO> {

    @Resource
    private MechDAO mechDAO;

    @Override
    public Long save(MechDTO saveObject) {
        CommonUtils.setInsertDefaultValue(saveObject);
        MechDO record = new MechDO();
        CommonUtils.copyProperties(saveObject, record);
        mechDAO.insert(record);
        return record.getId();
    }

    @Override
    public MechDTO queryById(Long id) {
        return null;
    }

    @Override
    public List<MechDTO> queryList(MechDTO query) {
        MechDO record = new MechDO();
        CommonUtils.copyProperties(query, record);
        List<MechDO> list = mechDAO.selectList(record);
        return CommonUtils.convertList(list, MechDTO.class);
    }

    @Override
    public int countQueryList(MechDTO query) {
        MechDO record = new MechDO();
        CommonUtils.copyProperties(query, record);
        return mechDAO.countSelectList(record);
    }

    @Override
    public MechDTO queryOne(MechDTO query) {
        return null;
    }

    @Override
    public int updateById(MechDTO updateObject) {
        return 0;
    }

    @Override
    public int deleteById(MechDTO deleteObject) {
        return 0;
    }
}
