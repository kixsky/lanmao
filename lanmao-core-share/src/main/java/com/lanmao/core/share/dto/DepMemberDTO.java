package com.lanmao.core.share.dto;

import com.lanmao.common.bean.BaseBean;
import lombok.Data;

@Data
public class DepMemberDTO extends BaseBean {

    private Long id;

    private String name;

    private String loginName;

    private String password;
}
