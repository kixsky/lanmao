package com.lanmao.core.dataobject;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.lanmao.common.bean.BaseBean;
import lombok.Data;

@Data
@TableName("lanmao_order_apply")
public class OrderApplyDO extends BaseBean {

    @TableId(type = IdType.AUTO)
    private Long id;

    private Long orderId;

    private Long guestId;

    private Long mechId;

    private Integer status;

    @TableField(exist = false)
    private Integer offset;

    @TableField(exist = false)
    private Integer limit;
}
