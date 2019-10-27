package com.lanmao.core.repository;

import com.lanmao.common.base.BaseRepository;
import com.lanmao.core.dataobject.GuestProductDO;
import com.lanmao.core.mapper.GuestProductDAO;
import com.lanmao.core.share.dto.GuestProductDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
@Slf4j
public class GuestProductRepository extends BaseRepository<GuestProductDTO> {

    @Resource
    private GuestProductDAO guestProductDAO;

    @Override
    public Long save(GuestProductDTO saveObject) {
        return null;
    }

    @Override
    public GuestProductDTO queryById(Long id) {
        return null;
    }

    @Override
    public List<GuestProductDTO> queryList(GuestProductDTO query) {
        return null;
    }

    @Override
    public GuestProductDTO queryOne(GuestProductDTO query) {
        return null;
    }

    @Override
    public int updateById(GuestProductDTO updateObject) {
        return 0;
    }

    @Override
    public int deleteById(GuestProductDTO deleteObject) {
        return 0;
    }
}
