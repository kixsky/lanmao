package com.lanmao.core.share.dto;

import com.lanmao.common.bean.BaseBean;
import lombok.Data;

import java.util.Date;

@Data
public class BookDTO extends BaseBean {

    private Long userId;

    private Long addressId;

    private String linkMobile;

    private String linkName;

    private Date bookTime;

}
