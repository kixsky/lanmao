package com.lanmao.core.share.dto;

import com.lanmao.common.bean.BaseBean;
import lombok.Data;

@Data
public class ShopDTO extends BaseBean {

    private Long id;

    private String name;

    private String imageUrl;

    private String mobilePhone;

    private String fixedTelephone;

    private Double lng;

    private Double lat;
}
