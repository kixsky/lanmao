package com.lanmao.core.repository;

import com.lanmao.common.base.BaseRepository;
import com.lanmao.common.utils.CommonUtils;
import com.lanmao.core.dataobject.ProductDO;
import com.lanmao.core.mapper.ProductDAO;
import com.lanmao.core.share.dto.ProductDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

@Slf4j
@Repository
public class ProductRepository extends BaseRepository<ProductDTO> {

    @Resource
    private ProductDAO productDAO;

    @Override
    public Long save(@NotNull ProductDTO saveObject) {
        ProductDO record = new ProductDO();
        CommonUtils.copyProperties(saveObject, record);
        CommonUtils.setInsertDefaultValue(record);
        productDAO.insert(record);
        return record.getId();
    }

    @Override
    public ProductDTO queryById(Long id) {
        ProductDO record = productDAO.selectById(id);
        if (record == null) {
            return null;
        }
        ProductDTO resultDTO = new ProductDTO();
        CommonUtils.copyProperties(record, resultDTO);
        return resultDTO;
    }

    @Override
    public List<ProductDTO> queryList(@NotNull ProductDTO query) {
        ProductDO record = new ProductDO();
        CommonUtils.copyProperties(query, record);
        List<ProductDO> list = productDAO.selectList(record);
        return CommonUtils.convertList(list, ProductDTO.class);
    }

    @Override
    public int countQueryList(ProductDTO query) {
        ProductDO record = new ProductDO();
        CommonUtils.copyProperties(query, record);
        return productDAO.countSelectList(record);
    }

    @Override
    public ProductDTO queryOne(ProductDTO query) {
        List<ProductDTO> list = queryList(query);
        if (CollectionUtils.isNotEmpty(list)) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public int updateById(ProductDTO updateObject) {
        ProductDO record = new ProductDO();
        CommonUtils.copyProperties(updateObject, record);
        return productDAO.updateById(record);
    }

    @Override
    public int deleteById(ProductDTO deleteObject) {
        ProductDO record = new ProductDO();
        CommonUtils.copyProperties(deleteObject, record);
        CommonUtils.setDeleteDefaultValue(record);
        return productDAO.updateById(record);
    }
}
