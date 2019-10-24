package com.lanmao.user.utils;

import lombok.Data;

@Data
public class WechatPayParams {

    private String body;

    private String detail;

    private String outTradeNo;

    private Integer totalFee;

    private String spbillCreateIp;

    private String openId;

    private String notifyUrl;
}
