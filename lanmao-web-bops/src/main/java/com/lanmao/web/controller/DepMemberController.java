package com.lanmao.web.controller;

import com.alibaba.fastjson.JSON;
import com.lanmao.common.annotation.IgnorePath;
import com.lanmao.common.bean.BaseResult;
import com.lanmao.common.constants.CommonConstants;
import com.lanmao.core.share.dto.DepMemberDTO;
import com.lanmao.core.share.service.DepMemberService;
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
@RequestMapping(value = "/v1/depMember")
public class DepMemberController {

    @Resource
    private DepMemberService depMemberService;

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
    BaseResult<String> login(@RequestBody DepMemberDTO loginDTO, HttpServletResponse response) {
        log.info("login: {}", JSON.toJSONString(loginDTO));
        BaseResult<String> baseResult = new BaseResult<>();
        baseResult.setCodeSuccess();
        BaseResult<DepMemberDTO> loginResult = depMemberService.login(loginDTO);
        if (loginResult.failed()) {
            baseResult.setCode(loginResult.getCode());
            baseResult.setMessage(loginResult.getMessage());
            return baseResult;
        }
        DepMemberDTO depMemberDTO = loginResult.getData();
        String accessToken = UUID.randomUUID().toString();
        redisTemplate.opsForValue().set(accessToken, JSON.toJSONString(depMemberDTO));
        Cookie cookie = new Cookie(CommonConstants.ACCESS_TOKEN, accessToken);
        response.addCookie(cookie);
        baseResult.setData(accessToken);
        return baseResult;
    }

}
