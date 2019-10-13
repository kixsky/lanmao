package com.lanmao.core.share.service;

import com.lanmao.common.bean.BaseResult;
import com.lanmao.core.share.constants.Constants;
import com.lanmao.core.share.dto.OrderDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = Constants.SERVICE_NAME)
@RequestMapping(value = "/api/order")
public interface OrderService {


    /**
     *
     * 新增订单
     * @param orderDTO
     * @return
     */
    @RequestMapping(value = "/addOrder", method = RequestMethod.POST)
    BaseResult<String> addOrder(@RequestBody OrderDTO orderDTO);
}
