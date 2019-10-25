package com.lanmao.core.dataobject;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.lanmao.common.bean.BaseBean;
import lombok.Data;

import java.util.Date;

@Data
@TableName("lanmao_user_coupon")
public class UserCouponDO extends BaseBean {

    @TableId(type = IdType.AUTO)
    private Long id;

    private Long userId;

    private Long couponId;

    private Integer status;

    private Date effectiveDate;

    private Date expiryDate;
}
