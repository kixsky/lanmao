package com.lanmao.core.dataobject;

import com.lanmao.common.bean.BaseBean;
import lombok.Data;

@Data
public class BrandDO extends BaseBean {

    private Long id;

    private String name;

    private String imageUrl;

    private String mobilePhone;

    private String fixedTelephone;
}
