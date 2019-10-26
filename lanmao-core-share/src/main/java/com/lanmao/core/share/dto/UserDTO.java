package com.lanmao.core.share.dto;

import com.lanmao.common.bean.BaseBean;
import lombok.Data;

import java.util.Date;

@Data
public class UserDTO extends BaseBean {

    private Long id;

    private String userNo;

    private String name;

    private String gender;

    private String avatar;

    private String address;

    private Date birthday;

    private String mobile;

    private String openId;

    private Integer offset;

    private Integer limit;
}
