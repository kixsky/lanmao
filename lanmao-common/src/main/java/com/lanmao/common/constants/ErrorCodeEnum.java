package com.lanmao.common.constants;

public enum ErrorCodeEnum {

    CODE_SUCCESS(0, "业务处理成功"),
    CODE_FAIL(500, "服务器内部错误"),

    //自定义CODE
    CODE_20001(20001, "业务异常"),
    CODE_302(302,"用户未登录");

    private Integer code;
    private String message;

    ErrorCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
