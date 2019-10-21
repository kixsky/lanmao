package com.lanmao.common.bean;

import com.lanmao.common.constants.ErrorCodeEnum;

public class BaseResult<T> {

    private Integer code;

    private String message;

    private T data;

    public void setCodeSuccess() {
        this.code = ErrorCodeEnum.CODE_SUCCESS.getCode();
        this.message = ErrorCodeEnum.CODE_SUCCESS.getMessage();
    }

    public static <T> BaseResult<T> success(T data) {
        BaseResult<T> baseResult = new BaseResult<>();
        baseResult.setCodeSuccess();
        baseResult.setData(data);
        return baseResult;
    }

    public static <T> BaseResult<T> fail(String errorMsg) {
        BaseResult<T> baseResult = new BaseResult<>();
        baseResult.setCode(ErrorCodeEnum.CODE_20001.getCode());
        baseResult.setMessage(errorMsg);
        return baseResult;
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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
