package com.lanmao.core.service;

import com.lanmao.common.bean.BaseResult;
import com.lanmao.common.constants.ErrorCodeEnum;
import com.lanmao.core.repository.OrderRepository;
import com.lanmao.core.share.dto.OrderDTO;
import com.lanmao.core.share.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderRepository orderRepository;

    @Override
    public BaseResult<Long> addOrder(@RequestBody OrderDTO orderDTO) {
        BaseResult<Long> baseResult = new BaseResult<>();
        baseResult.setCode(ErrorCodeEnum.CODE_SUCCESS.getCode());
        try {
            Long newId = orderRepository.save(orderDTO);
            baseResult.setData(newId);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return baseResult;
    }
}
