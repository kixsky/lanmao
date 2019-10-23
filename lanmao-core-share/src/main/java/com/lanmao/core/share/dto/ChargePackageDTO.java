package com.lanmao.core.share.dto;

import com.lanmao.common.bean.BaseBean;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ChargePackageDTO extends BaseBean {

    private Long id;

    private String packageName;

    private BigDecimal chargeAmount;

    private BigDecimal donationAmount;
}
