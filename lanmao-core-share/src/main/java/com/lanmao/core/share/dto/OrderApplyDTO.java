package com.lanmao.core.share.dto;

import com.lanmao.common.bean.BaseBean;
import lombok.Data;

@Data
public class OrderApplyDTO extends BaseBean {

    private Long id;

    private Long orderId;

    private Long guestId;

    private Long mechId;

    private Integer status;

    private Integer offset;

    private Integer limit;
}
