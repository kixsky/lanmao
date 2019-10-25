package com.lanmao.core.dataobject;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.lanmao.common.bean.BaseBean;
import lombok.Data;

import java.util.Date;

@Data
@TableName("lanmao_mech")
public class MechDO extends BaseBean {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String name;

    private String avatar;

    private String gender;

    private String jobNum;

    private Long mchId;

    private String loginName;

    private String password;

    private String address;

    private String nativeAddress;

    private String stageName;

    private String mobile;

    private String remark;

    private Date birthday;

    private String contact;

    private String contactMobile;

    private String contactLink;

    private Date entryTime;

    private Integer status;

    private String starLevel;

    //========非数据库字段=======================

    @TableField(exist = false)
    private Integer offset;

    @TableField(exist = false)
    private Integer limit;
}
