package com.lanmao.user.controller;

import com.lanmao.common.annotation.IgnorePath;
import com.lanmao.common.bean.BaseResult;
import com.lanmao.common.constants.ErrorCodeEnum;
import com.lanmao.core.share.service.SmsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
@RequestMapping(value = "/v1/sms")
public class SmsController {

    @Resource
    private SmsService smsService;

    /**
     *
     * 发送验证码
     * @param mobile
     * @return
     */
    @IgnorePath
    @RequestMapping(value = "/sendCode", method = RequestMethod.POST)
    public BaseResult<String> sendCode(@RequestParam("mobile") String mobile) {
        BaseResult<String> baseResult = new BaseResult<>();
        try {
            baseResult = smsService.sendCode(mobile);
        } catch (Exception e) {
            log.info("sendCode", e);
            baseResult.setCode(ErrorCodeEnum.CODE_FAIL.getCode());
            baseResult.setMessage(e.getMessage());
        }
        return baseResult;
    }
}
