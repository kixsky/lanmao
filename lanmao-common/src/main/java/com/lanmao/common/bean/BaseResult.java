package com.lanmao.common.bean;

import com.lanmao.common.constants.ErrorCodeEnum;
import lombok.Data;

@Data
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

    public boolean failed() {
        return !ErrorCodeEnum.CODE_SUCCESS.getCode().equals(this.code);
    }
}
