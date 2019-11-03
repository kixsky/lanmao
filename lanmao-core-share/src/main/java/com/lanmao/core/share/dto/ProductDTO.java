package com.lanmao.core.share.dto;

import com.lanmao.common.bean.BaseBean;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductDTO extends BaseBean {

    private Long id;

    private String name;

    private BigDecimal price;

    private BigDecimal sellPrice;

    private String imageUrl;

    private Integer duration;

    private Integer status;

    private String proDesc;

    private String isRec;

    private String isNew;
}
