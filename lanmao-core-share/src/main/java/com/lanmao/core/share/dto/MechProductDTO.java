package com.lanmao.core.share.dto;

import com.lanmao.common.bean.BaseBean;
import lombok.Data;

@Data
public class MechProductDTO extends BaseBean {

    private Long id;

    private Long mechId;

    private Long productId;
}
