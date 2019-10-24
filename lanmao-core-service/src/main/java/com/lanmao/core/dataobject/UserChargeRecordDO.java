package com.lanmao.core.dataobject;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.lanmao.common.bean.BaseBean;
import lombok.Data;

@Data
@TableName("lanmao_user_charge_record")
public class UserChargeRecordDO extends BaseBean {

    @TableId(type = IdType.AUTO)
    private Long id;

    private Long userId;

    private Long packageId;

    private String tradeNo;

    private Integer status;

    private String outTradeNo;

    private String payBackJson;
}
