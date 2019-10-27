package com.lanmao.mech.controller;

import com.alibaba.fastjson.JSON;
import com.lanmao.common.annotation.IgnorePath;
import com.lanmao.common.bean.BaseResult;
import com.lanmao.common.constants.CommonConstants;
import com.lanmao.common.exception.BusinessException;
import com.lanmao.core.share.dto.MechDTO;
import com.lanmao.core.share.service.MechService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@RestController
@Slf4j
@RequestMapping(value = "/v1")
public class LoginController {

    @Resource
    private MechService mechService;

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    /**
     *
     * 登陆
     * @param loginDTO
     * @return
     */
    @IgnorePath
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public BaseResult<String> login(@RequestBody MechDTO loginDTO, HttpServletResponse response) {
        BaseResult<String> baseResult = new BaseResult<>();
        baseResult.setCodeSuccess();
        BaseResult<MechDTO> loginResult = mechService.login(loginDTO);
        if (loginResult.failed()) {
            throw new BusinessException(loginResult.getCode(), loginResult.getMessage());
        }
        MechDTO mechDTO = loginResult.getData();
        String accessToken = UUID.randomUUID().toString();
        redisTemplate.opsForValue().set(accessToken, JSON.toJSONString(mechDTO));
        Cookie cookie = new Cookie(CommonConstants.ACCESS_TOKEN, accessToken);
        cookie.setMaxAge(30 * 12 * 3600);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        response.addCookie(cookie);
        baseResult.setData(accessToken);
        return baseResult;
    }
}
