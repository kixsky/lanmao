package com.lanmao.core.share.dto;

import com.lanmao.common.bean.BaseBean;
import lombok.Data;

@Data
public class SmsDTO extends BaseBean {

    private String mobile;

    private String code;
}
