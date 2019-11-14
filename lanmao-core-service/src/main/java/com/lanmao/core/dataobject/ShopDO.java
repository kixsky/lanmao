package com.lanmao.core.dataobject;

import com.lanmao.common.bean.BaseBean;
import lombok.Data;

@Data
public class ShopDO extends BaseBean {

    private Long id;

    private String name;

    private String imageUrl;

    private String mobilePhone;

    private String fixedTelephone;

    private Double lng;

    private Double lat;
}
