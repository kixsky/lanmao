package com.lanmao.user.controller;

import com.lanmao.core.share.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
@RequestMapping(value = "/v1/login")
public class LoginController {

    @Resource
    private UserService userService;


}
