package com.lanmao.common.utils;

import com.lanmao.common.bean.BaseBean;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;

public class CommonUtils {

    /**
     *
     * 设置默认值
     * @param record
     */
    public static final void setDefaultValue(BaseBean record) {
        if (StringUtils.isEmpty(record.getCreator())) {
            record.setCreator("system");
        }
        if (record.getGmtCreated() == null) {
            record.setGmtCreated(new Date());
        }
        if (record.getGmtModified() == null) {
            record.setGmtModified(new Date());
        }
        if (StringUtils.isEmpty(record.getModifier())) {
            record.setModifier("system");
        }
        if (StringUtils.isEmpty(record.getIsDeleted())) {
            record.setIsDeleted("N");
        }
    }
}
