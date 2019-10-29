package com.lanmao.core.share.dto;

import com.lanmao.common.bean.BaseBean;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class UserCouponDTO extends BaseBean {

    private Long id;

    private Long userId;

    private Long couponId;

    private Integer status;

    private String name; //名称

    private Date effectiveDate; //有效期起期

    private Date expiryDate; //有限期止期

    private BigDecimal discount; //优惠金额

    private String ruleCode; //规则code

    private String ruleName; //规则名称

    private Integer type; //类型
}
