package com.lanmao.core.repository;

import com.lanmao.common.base.BaseRepository;
import com.lanmao.common.utils.CommonUtils;
import com.lanmao.core.dataobject.ProductDO;
import com.lanmao.core.mapper.MechProductDAO;
import com.lanmao.core.share.dto.MechProductDTO;
import com.lanmao.core.share.dto.ProductDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
@Slf4j
public class MechProductRepository extends BaseRepository<MechProductDTO> {

    @Resource
    private MechProductDAO mechProductDAO;

    @Override
    public Long save(MechProductDTO saveObject) {
        return null;
    }

    @Override
    public MechProductDTO queryById(Long id) {
        return null;
    }

    @Override
    public List<MechProductDTO> queryList(MechProductDTO query) {
        return null;
    }

    @Override
    public MechProductDTO queryOne(MechProductDTO query) {
        return null;
    }

    @Override
    public int updateById(MechProductDTO updateObject) {
        return 0;
    }

    @Override
    public int deleteById(MechProductDTO deleteObject) {
        return 0;
    }

    public List<ProductDTO> queryMechProduct(Long mechId) {
        List<ProductDO> list = mechProductDAO.selectProduct(mechId);
        return CommonUtils.convertList(list, ProductDTO.class);
    }
}
