package com.lanmao.user.config;

import com.lanmao.common.bean.BaseResult;
import com.lanmao.common.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * 全局异常处理器
 */
@ControllerAdvice
@Slf4j
public class ExceptionAdvice {

    /**
     *
     * 异常处理方法
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public BaseResult handleException(HttpServletRequest req, Exception e) {
        log.info("请求路径: {}", req.getServletPath());
        log.info("Exception,",e);
        return handleException(e);
    }


    private BaseResult handleException(Exception e) {
        if (e instanceof BusinessException) {
            BusinessException bus = (BusinessException)e;
            BaseResult baseResult = new BaseResult();
            baseResult.setCode(bus.getCode());
            baseResult.setMessage(bus.getMessage());
            return baseResult;
        } else {
            BaseResult baseResult = new BaseResult();
            baseResult.setCode(500);
            baseResult.setMessage("系统异常");
            return baseResult;
        }
    }
}
