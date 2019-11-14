package com.lanmao.core.repository;

import com.lanmao.common.base.BaseRepository;
import com.lanmao.common.utils.CommonUtils;
import com.lanmao.core.dataobject.ShopDO;
import com.lanmao.core.mapper.ShopDAO;
import com.lanmao.core.share.dto.ShopDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Slf4j
public class ShopRepository extends BaseRepository<ShopDTO> {

    @Resource
    private ShopDAO shopDAO;

    @Override
    public Long save(ShopDTO saveObject) {
        ShopDO record = new ShopDO();
        CommonUtils.copyProperties(saveObject, record);
        CommonUtils.setInsertDefaultValue(record);
        shopDAO.insert(record);
        return record.getId();
    }

    @Override
    public ShopDTO queryById(Long id) {
        ShopDO record = shopDAO.selectById(id);
        if (record == null) {
            return null;
        }
        ShopDTO resultDTO = new ShopDTO();
        CommonUtils.copyProperties(record, resultDTO);
        return resultDTO;
    }

    @Override
    public List<ShopDTO> queryList(ShopDTO query) {
        ShopDO record = new ShopDO();
        CommonUtils.copyProperties(query, record);
        List<ShopDO> recordList = shopDAO.selectListByDO(record);
        return CommonUtils.convertList(recordList, ShopDTO.class);
    }

    @Override
    public int countQueryList(ShopDTO query) {
        return 0;
    }

    @Override
    public ShopDTO queryOne(ShopDTO query) {
        return null;
    }

    @Override
    public int updateById(ShopDTO updateObject) {
        return 0;
    }

    @Override
    public int deleteById(ShopDTO deleteObject) {
        return 0;
    }
}
