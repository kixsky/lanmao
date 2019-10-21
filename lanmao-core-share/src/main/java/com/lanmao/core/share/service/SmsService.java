package com.lanmao.core.share.service;

import com.lanmao.common.bean.BaseResult;
import com.lanmao.core.share.constants.Constants;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = Constants.SERVICE_NAME)
@RequestMapping(value = "/api/sms")
public interface SmsService {

    /**
     *
     * 发送验证码
     * @param mobile
     * @return
     */
    @RequestMapping(value = "/sendCode", method = RequestMethod.POST)
    BaseResult<String> sendCode(@RequestParam("mobile") String mobile);
}
