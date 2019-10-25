package com.lanmao.core.share.dto;

import com.lanmao.common.bean.BaseBean;
import lombok.Data;

import java.util.Date;

@Data
public class MechDTO extends BaseBean {

    private Long id;

    private String name;

    private String avatar;

    private String gender;

    private String jobNum;

    private String address;

    private String nativeAddress;

    private String stageName;

    private String mobile;

    private String remark;

    private Date birthday;

    private String contact;

    private String contactMobile;

    private String contactLink;

    private Date entryTime;

    private Integer status;

    private String starLevel;

    private Integer offset;

    private Integer limit;
}
