package com.lanmao.core.dataobject;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.lanmao.common.bean.BaseBean;
import lombok.Data;

@Data
@TableName("lanmao_mech_product")
public class MechProductDO extends BaseBean {

    @TableId(type = IdType.AUTO)
    private Long id;

    private Long mechId;

    private Long productId;
}
