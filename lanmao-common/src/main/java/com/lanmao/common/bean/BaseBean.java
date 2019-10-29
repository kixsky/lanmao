package com.lanmao.common.bean;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.Date;

@Data
public class BaseBean {

    private Date gmtCreated;

    private Date gmtModified;

    private String creator;

    private String modifier;

    private String isDeleted;

    @TableField(exist = false)
    private Integer offset;

    @TableField(exist = false)
    private Integer limit;
}
