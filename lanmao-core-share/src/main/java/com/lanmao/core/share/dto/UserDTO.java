package com.lanmao.core.share.dto;

import com.lanmao.common.bean.BaseBean;
import lombok.Data;

@Data
public class UserDTO extends BaseBean {

    private Long id;

    private String userNo;

    private String name;

    private String sex;

    private String avatar;

    private String address;
}
