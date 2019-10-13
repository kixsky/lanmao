package com.lanmao.user.auth;

import com.alibaba.fastjson.JSON;
import com.lanmao.common.annotation.IgnorePath;
import com.lanmao.common.constants.CommonConstants;
import com.lanmao.common.constants.ErrorCodeEnum;
import com.lanmao.common.exception.BusinessException;
import com.lanmao.core.share.dto.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class AuthInterceptor extends HandlerInterceptorAdapter {

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) {

        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod method = (HandlerMethod) handler;
        if (method.getBeanType().getAnnotation(IgnorePath.class) != null ||
            method.getMethod().getAnnotation(IgnorePath.class) != null) {
            return true;
        }

        String token = req.getHeader(CommonConstants.TOKEN);
        log.info("token: {}", token);

        //未登录
        if (StringUtils.isBlank(token)) {
            throw new BusinessException(ErrorCodeEnum.CODE_302);
        }

        String jsonData = redisTemplate.opsForValue().get(token);
        log.info("jsonData: {}", jsonData);
        if (StringUtils.isBlank(jsonData)) {
            throw new BusinessException(ErrorCodeEnum.CODE_302);
        }

        UserDTO userDto = JSON.parseObject(jsonData, UserDTO.class);
        log.info("userDto : {}", JSON.toJSONString(userDto));

        LoginUser user = new LoginUser();
        BeanUtils.copyProperties(userDto, user);
        LoginHolder.set(user);
        return true;
    }

}
