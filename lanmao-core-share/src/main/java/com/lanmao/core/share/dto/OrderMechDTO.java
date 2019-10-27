package com.lanmao.core.share.dto;

import com.lanmao.common.bean.BaseBean;
import lombok.Data;

@Data
public class OrderMechDTO extends BaseBean {

    private Long id;

    private Long orderId;

    private Long mechId;

    private String status;
}
