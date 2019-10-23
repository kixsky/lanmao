package com.lanmao.core.repository;

import com.lanmao.common.base.BaseRepository;
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
        return null;
    }

    @Override
    public ChargePackageDTO queryById(Long id) {
        return null;
    }

    @Override
    public List<ChargePackageDTO> queryList(ChargePackageDTO query) {
        return null;
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
