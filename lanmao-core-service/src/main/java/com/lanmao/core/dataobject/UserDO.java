package com.lanmao.core.dataobject;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.lanmao.common.bean.BaseBean;
import lombok.Data;

import java.util.Date;

@Data
@TableName("lanmao_user")
public class UserDO extends BaseBean {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String userNo;

    private String name;

    private String gender;

    private String avatar;

    private String address;

    private Date birthday;

    private String mobile;

    private String openId;

    //=================非数据库字段==========

    @TableField(exist = false)
    private Integer offset;

    @TableField(exist = false)
    private Integer limit;
}
