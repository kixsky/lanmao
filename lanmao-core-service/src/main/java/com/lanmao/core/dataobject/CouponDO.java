package com.lanmao.core.dataobject;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.lanmao.common.bean.BaseBean;
import lombok.Data;

@Data
public class CouponDO extends BaseBean {

    @TableId(type = IdType.AUTO)
    private Long id;
}
