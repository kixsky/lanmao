package com.lanmao.core.share.dto;

import com.lanmao.common.bean.BaseBean;
import lombok.Data;

@Data
public class GuestProductDTO extends BaseBean {

    private Long id;

    private Long productId;

    private Long mechId;

    private Integer payStatus;
}
