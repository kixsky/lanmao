package com.lanmao.core.share.dto;

import com.lanmao.common.bean.BaseBean;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class CouponDTO extends BaseBean {

    private Long id;

    private String name; //名称

    private Date effectiveDate; //有效期起期

    private Date expiryDate; //有限期止期

    private BigDecimal discount; //优惠金额

    private String ruleCode; //规则code

    private Integer type; //类型
}
