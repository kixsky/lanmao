package com.lanmao.common.exception;


import com.lanmao.common.constants.ErrorCodeEnum;

public class BusinessException extends RuntimeException {

    private Integer code;

    public BusinessException() {

    }

    public BusinessException(String message) {
        super(message);
        code = ErrorCodeEnum.CODE_20001.getCode();
    }

    public BusinessException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public BusinessException(ErrorCodeEnum errorCodeEnum) {
        super(errorCodeEnum.getMessage());
        this.code = errorCodeEnum.getCode();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
