package com.lanmao.common.utils;

import com.lanmao.common.bean.BaseBean;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.Field;
import java.util.*;

public class CommonUtils {

    private static final Logger logger = LoggerFactory.getLogger(CommonUtils.class);

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

    /**
     *
     *  拷贝对象
     * @param source
     * @param target
     */
    public static final void copyProperties(Object source, Object target) {
        BeanUtils.copyProperties(source, target);
    }


    /**
     *
     *  对象转map
     * @param target
     * @return
     */
    public static final Map<String, Object> objToMap(Object target) {
        try {
            Map<String, Object> result = new HashMap<>();
            Field[] declaredFields = target.getClass().getDeclaredFields();
            for (Field field : declaredFields) {
                field.setAccessible(true);
                result.put(field.getName(), field.get(target));
            }
            return result;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }


    /**
     * list 对象复制
     * @param list
     * @param tClass
     * @param <T>
     * @return
     */
    public static <T> List<T> convertList(List<?> list, Class<T> tClass) {
        try {
            List<T> resultList = new ArrayList<>();
            if (CollectionUtils.isNotEmpty(list)) {
                for (Object src: list) {
                    T tInstance = tClass.newInstance();
                    copyProperties(src, tInstance);
                    resultList.add(tInstance);
                }
            }
            return resultList;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }


    /**
     *
     * 转下划线
     * @param str
     * @return
     */
    public static final String toUnderLine(String str) {
        char[] chars = str.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < chars.length; i++) {
            if (Character.isUpperCase(chars[i])) {
                sb.append("_");
            }
            sb.append(chars[i]);
        }
        return sb.toString().toLowerCase();
    }


    /**
     *
     *  对象转map
     * @param target
     * @return
     */
    public static final Map<String, Object> toQueryMap(Object target) {
        try {
            Map<String, Object> result = new HashMap<>();
            Class tempClass = target.getClass();
            while(tempClass != null) {
                Field[] declaredFields = tempClass.getDeclaredFields();
                for (Field field : declaredFields) {
                    field.setAccessible(true);
                    String fieldName = field.getName();
                    Object fieldObj = field.get(target);
                    if (fieldObj != null) {
                        String underLineStr = toUnderLine(fieldName);
                        result.put(underLineStr, fieldObj);
                    }
                }
                tempClass = tempClass.getSuperclass();
            }
            return result;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }
}
