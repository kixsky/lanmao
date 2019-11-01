package com.lanmao.core.repository;

import com.lanmao.common.base.BaseRepository;
import com.lanmao.common.utils.CommonUtils;
import com.lanmao.core.dataobject.ChargePackageDO;
import com.lanmao.core.mapper.ChargePackageDAO;
import com.lanmao.core.share.dto.ChargePackageDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
@Slf4j
public class ChargePackageRepository extends BaseRepository<ChargePackageDTO> {

    @Resource
    private ChargePackageDAO chargePackageDAO;

    @Override
    public Long save(ChargePackageDTO saveObject) {
        CommonUtils.setInsertDefaultValue(saveObject);
        ChargePackageDO record = new ChargePackageDO();
        CommonUtils.copyProperties(saveObject, record);
        chargePackageDAO.insert(record);
        return record.getId();
    }

    @Override
    public ChargePackageDTO queryById(Long id) {
        ChargePackageDO record = chargePackageDAO.selectById(id);
        if (record == null) {
            return null;
        }
        ChargePackageDTO resultDTO = new ChargePackageDTO();
        CommonUtils.copyProperties(record, resultDTO);
        return resultDTO;
    }

    @Override
    public List<ChargePackageDTO> queryList(ChargePackageDTO query) {
        ChargePackageDO record = new ChargePackageDO();
        CommonUtils.copyProperties(query, record);
        List<ChargePackageDO> list = chargePackageDAO.selectList(record);
        return CommonUtils.convertList(list, ChargePackageDTO.class);
    }

    @Override
    public int countQueryList(ChargePackageDTO query) {
        return 0;
    }

    @Override
    public ChargePackageDTO queryOne(ChargePackageDTO query) {
        return null;
    }

    @Override
    public int updateById(ChargePackageDTO updateObject) {
        return 0;
    }

    @Override
    public int deleteById(ChargePackageDTO deleteObject) {
        return 0;
    }
}
