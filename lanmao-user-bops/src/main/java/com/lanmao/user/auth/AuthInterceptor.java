package com.lanmao.user.auth;

import com.alibaba.fastjson.JSON;
import com.lanmao.common.annotation.IgnorePath;
import com.lanmao.common.constants.CommonConstants;
import com.lanmao.common.constants.ErrorCodeEnum;
import com.lanmao.common.exception.BusinessException;
import com.lanmao.core.share.dto.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class AuthInterceptor extends HandlerInterceptorAdapter {

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) {
        log.info(req.getServletPath());
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod method = (HandlerMethod) handler;
        if (method.getBeanType().getAnnotation(IgnorePath.class) != null ||
            method.getMethod().getAnnotation(IgnorePath.class) != null) {
            return true;
        }
        String accessToken = getAccessToken(req);
        log.info("token: {}", accessToken);
        //未登录
        if (StringUtils.isBlank(accessToken)) {
            throw new BusinessException(ErrorCodeEnum.CODE_302);
        }
        String jsonData = redisTemplate.opsForValue().get(accessToken);
        if (StringUtils.isBlank(jsonData)) {
            throw new BusinessException(ErrorCodeEnum.CODE_302);
        }
        UserDTO userDto = JSON.parseObject(jsonData, UserDTO.class);
        LoginHolder.set(userDto);
        return true;
    }


    private String getAccessToken(HttpServletRequest req) {
        Cookie[] cookies = req.getCookies();
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie: cookies) {
                if (CommonConstants.ACCESS_TOKEN.equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

}
