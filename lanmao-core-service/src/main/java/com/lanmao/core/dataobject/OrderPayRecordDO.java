package com.lanmao.core.dataobject;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.lanmao.common.bean.BaseBean;
import lombok.Data;

import java.math.BigDecimal;

@Data
@TableName("lanmao_order_pay_record")
public class OrderPayRecordDO extends BaseBean {

    @TableId(type = IdType.AUTO)
    private Long id;

    private Long userId;

    private Long orderId;

    private BigDecimal payAmount;

    private Long couponId;

    private Integer status;

    private Integer payType;

    private String tradeNo;

    private String outTradeNo;

    private String payBackJson;
}
