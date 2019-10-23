package com.lanmao.core.share.dto;

import com.lanmao.common.bean.BaseBean;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class UserWalletDTO extends BaseBean {

    private Long id;

    private Long userId;

    private String mobile;

    private BigDecimal balance;
}
