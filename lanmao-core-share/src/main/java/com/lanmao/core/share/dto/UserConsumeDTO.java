package com.lanmao.core.share.dto;

import com.lanmao.common.bean.BaseBean;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class UserConsumeDTO extends BaseBean {

    private Long id;

    private Long userId;

    private Long orderId;

    private BigDecimal payAmount;

    private String remark;
}
