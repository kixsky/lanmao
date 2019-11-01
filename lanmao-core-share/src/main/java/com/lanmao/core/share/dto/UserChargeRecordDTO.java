package com.lanmao.core.share.dto;

import com.lanmao.common.bean.BaseBean;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Map;

@Data
public class UserChargeRecordDTO extends BaseBean {

    private Long id;

    private Long userId;

    private Long packageId;

    private String tradeNo;

    private Integer status;

    private BigDecimal chargeAmount; //充值金额

    private BigDecimal donationAmount; //赠送金额

    private String outTradeNo; //微信交易号

    private String payBackJson;

    private String openId;

    private Map<String, String> payParams;
}
