package com.lanmao.user.controller;

import com.alibaba.fastjson.JSON;
import com.lanmao.common.annotation.IgnorePath;
import com.lanmao.common.bean.BaseResult;
import com.lanmao.core.share.dto.LoginDTO;
import com.lanmao.core.share.dto.UserDTO;
import com.lanmao.core.share.service.UserService;
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
    private UserService userService;

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    /**
     *
     * 登录
     * @param loginDTO
     * @return
     */
    @IgnorePath
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public BaseResult<String> login(@RequestBody LoginDTO loginDTO, HttpServletResponse response) {
        BaseResult<String> baseResult = new BaseResult<>();
        baseResult.setCodeSuccess();
        BaseResult<UserDTO> loginResult = userService.login(loginDTO);
        if (loginResult.failed()) {
            baseResult.setCode(loginResult.getCode());
            baseResult.setMessage(loginResult.getMessage());
            return baseResult;
        }
        UserDTO loginUserDTO = loginResult.getData();
        String accessToken = UUID.randomUUID().toString();
        redisTemplate.opsForValue().set(accessToken, JSON.toJSONString(loginUserDTO));
        Cookie cookie = new Cookie("accessToken", accessToken);
        response.addCookie(cookie);
        return baseResult;
    }
}
