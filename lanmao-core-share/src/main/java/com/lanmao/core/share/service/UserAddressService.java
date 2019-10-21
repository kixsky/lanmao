package com.lanmao.core.share.service;

import com.lanmao.core.share.constants.Constants;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = Constants.SERVICE_NAME)
@RequestMapping(value = "/api/userAddress")
public interface UserAddressService{
}
