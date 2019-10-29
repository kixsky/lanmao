package com.lanmao.common.constants;

public enum GenderEnum {

    F("F", "女"),
    M("M", "男");

    private String code;

    private String name;

    GenderEnum(String code, String name) {
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
