package com.lanmao.core.share.dto;

import com.lanmao.common.bean.BaseBean;
import lombok.Data;

@Data
public class BrandDTO extends BaseBean {

    private Long id;

    private String name;

    private String imageUrl;

    private String mobilePhone;

    private String fixedTelephone;
}
