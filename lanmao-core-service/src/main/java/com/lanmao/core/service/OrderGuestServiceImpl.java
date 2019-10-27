package com.lanmao.core.service;

import com.lanmao.common.bean.BaseResult;
import com.lanmao.common.bean.PageDTO;
import com.lanmao.core.repository.OrderGuestRepository;
import com.lanmao.core.share.dto.OrderGuestDTO;
import com.lanmao.core.share.service.OrderGuestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Slf4j
public class OrderGuestServiceImpl implements OrderGuestService {

    @Resource
    private OrderGuestRepository orderGuestRepository;

    @Override
    public BaseResult<Long> save(@RequestBody OrderGuestDTO saveObject) {
        return null;
    }

    @Override
    public BaseResult<List<OrderGuestDTO>> queryList(@RequestBody OrderGuestDTO query) {
        return null;
    }

    @Override
    public BaseResult<PageDTO<OrderGuestDTO>> queryPage(@RequestBody PageDTO<OrderGuestDTO> pageDTO) {
        return null;
    }
}
