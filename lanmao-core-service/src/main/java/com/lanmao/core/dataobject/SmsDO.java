package com.lanmao.core.dataobject;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.lanmao.common.bean.BaseBean;
import lombok.Data;

@Data
@TableName("sms")
public class SmsDO extends BaseBean {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String mobile;

    private String code;
}
