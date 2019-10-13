package com.lanmao.core.dataobject;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.lanmao.common.bean.BaseBean;
import lombok.Data;

@Data
@TableName("user")
public class UserDO extends BaseBean {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String userNo;

    private String name;

    private String sex;

    private String avatar;

    private String address;
}
