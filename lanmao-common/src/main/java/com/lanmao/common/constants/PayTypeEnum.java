package com.lanmao.common.constants;

public enum PayTypeEnum {

    WEIXIN(1, "微信"),
    ZHIFUBAO(2, "支付宝");


    private Integer code;
    private String name;

    PayTypeEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
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
