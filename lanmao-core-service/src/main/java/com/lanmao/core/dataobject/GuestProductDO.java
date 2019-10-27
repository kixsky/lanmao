package com.lanmao.core.dataobject;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.lanmao.common.bean.BaseBean;
import lombok.Data;

@Data
@TableName("lanmao_guest_product")
public class GuestProductDO extends BaseBean {

    @TableId(type = IdType.AUTO)
    private Long id;

    private Long guestId;

    private Long productId;

    private Long mechId;

    private Integer payStatus;

    @TableField(exist = false)
    private Integer offset;

    @TableField(exist = false)
    private Integer limit;
}
