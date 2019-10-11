package com.lanmao.web.controller;

import com.lanmao.common.bean.BaseResult;
import com.lanmao.core.share.dto.UserDTO;
import com.lanmao.core.share.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "/v1/user")
public class UserController {

    @Resource
    private UserService userService;

    @RequestMapping(value = "/getUser")
    BaseResult<UserDTO> getUser() {
        return userService.query(new UserDTO());
    }
}
