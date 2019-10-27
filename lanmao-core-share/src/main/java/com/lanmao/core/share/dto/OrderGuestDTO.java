package com.lanmao.core.share.dto;

import com.lanmao.common.bean.BaseBean;
import lombok.Data;

@Data
public class OrderGuestDTO extends BaseBean {

    private Long id;

    private String guestName;

    private String guestGender;
}
