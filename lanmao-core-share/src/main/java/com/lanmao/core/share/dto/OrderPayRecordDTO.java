package com.lanmao.core.share.dto;

import com.lanmao.common.bean.BaseBean;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderPayRecordDTO extends BaseBean {

    private Long id;

    private Long userId;

    private Long orderId;

    private BigDecimal payAmount;

    private Long couponId;

    private Integer status;

    private Integer payType;

    private String tradeNo;

    private String outTradeNo;

    private String payBackJson;
}
