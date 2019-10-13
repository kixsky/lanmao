package com.lanmao.common.constants;

public enum YesOrNoEnum {

    YES("Y", "是"),
    NO("N", "否");

    private String code;
    private String name;

    YesOrNoEnum(String code, String name) {
        this.code = code;
        this.name = name;
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
