package com.lanmao.core.service;

import com.lanmao.common.bean.BaseResult;
import com.lanmao.core.share.dto.ShopDTO;
import com.lanmao.core.share.service.ShopService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class ShopServiceImpl implements ShopService {
    @Override
    public BaseResult<Long> save(ShopDTO shopDTO) {
        return null;
    }
}
