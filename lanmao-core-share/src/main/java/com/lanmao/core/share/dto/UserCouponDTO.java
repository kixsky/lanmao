package com.lanmao.core.share.dto;

import com.lanmao.common.bean.BaseBean;
import lombok.Data;

import java.util.Date;

@Data
public class UserCouponDTO extends BaseBean {

    private Long id;

    private Long userId;

    private Long couponId;

    private Integer status;

    private Date effectiveDate;

    private Date expiryDate;
}
