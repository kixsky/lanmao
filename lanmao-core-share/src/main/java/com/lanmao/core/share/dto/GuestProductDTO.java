package com.lanmao.core.share.dto;

import com.lanmao.common.bean.BaseBean;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class GuestProductDTO extends BaseBean {

    private Long id;

    private Long guestId;

    private Long productId;

    private Long mechId;

    private String productName;

    private BigDecimal productPrice;

    private Integer payStatus;
}
