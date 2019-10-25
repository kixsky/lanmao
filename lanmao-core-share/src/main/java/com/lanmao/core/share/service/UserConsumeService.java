package com.lanmao.core.share.service;

import com.lanmao.common.bean.BaseResult;
import com.lanmao.core.share.constants.Constants;
import com.lanmao.core.share.dto.UserConsumeDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = Constants.SERVICE_NAME)
@RequestMapping(value = "/api/userConsume")
public interface UserConsumeService {


    /**
     *
     * 新增
     * @param saveObject
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    BaseResult<Long> save(@RequestBody UserConsumeDTO saveObject);
}
