package com.lanmao.common.constants;

import java.util.Objects;

public enum CouponRuleEnum {

    WU_MEN_KAN("1", "无门槛优惠券");

    private String code;

    private String name;

    CouponRuleEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static CouponRuleEnum getByCode(String code) {
        for (CouponRuleEnum typeEnum: CouponRuleEnum.values()) {
            if (Objects.equals(code, typeEnum.code)) {
                return typeEnum;
            }
        }
        return null;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
