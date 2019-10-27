package com.lanmao.core.share.dto;

import com.lanmao.common.bean.BaseBean;
import lombok.Data;

import java.util.Date;

@Data
public class OrderDTO extends BaseBean {

    private Long id;

    private Long userId;

    private String orderNo;

    private String address;

    private String linkMobile;

    private String linkName;

    private Date bookTime;

    private String status;

    private String remark;

    private String userRemark;

    private Date finishTime;


}
