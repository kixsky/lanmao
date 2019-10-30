package com.lanmao.common.constants;

public enum CouponTypeEnum {

    DAO_JIA(1, "到家"),
    ON_SHOP(2, "到店");

    private Integer code;

    private String name;

    CouponTypeEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public static CouponTypeEnum getByCode(Integer code) {
        for (CouponTypeEnum typeEnum: CouponTypeEnum.values()) {
            if (code == typeEnum.code) {
                return typeEnum;
            }
        }
        return null;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
