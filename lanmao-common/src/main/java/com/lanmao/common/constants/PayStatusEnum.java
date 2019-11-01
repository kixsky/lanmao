package com.lanmao.common.constants;

public enum PayStatusEnum {

    NOT_PAY(1, "未支付"),
    PAY(2, "已支付");

    private Integer code;

    private String name;

    PayStatusEnum(Integer code, String name) {
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
