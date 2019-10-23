package com.lanmao.core.dataobject;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.lanmao.common.bean.BaseBean;
import lombok.Data;

import java.math.BigDecimal;

@Data
@TableName("lanmao_charge_package")
public class ChargePackageDO extends BaseBean {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String packageName;

    private BigDecimal chargeAmount;

    private BigDecimal donationAmount;
}
