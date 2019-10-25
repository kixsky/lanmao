package com.lanmao.core.dataobject;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.lanmao.common.bean.BaseBean;
import lombok.Data;

import java.math.BigDecimal;

@Data
@TableName(value = "lanmao_product")
public class ProductDO extends BaseBean {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String name;

    private BigDecimal price;

    private BigDecimal sellPrice;

    private String imageUrl;

    private Integer duration;

    private Integer status;

    private String isRec;

    private String isNew;

    @TableField(exist = false)
    private Integer offset;

    @TableField(exist = false)
    private Integer limit;
}
