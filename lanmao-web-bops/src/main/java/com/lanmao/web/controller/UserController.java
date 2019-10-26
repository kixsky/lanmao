package com.lanmao.web.controller;

import com.lanmao.common.bean.BaseResult;
import com.lanmao.common.bean.PageDTO;
import com.lanmao.core.share.dto.UserDTO;
import com.lanmao.core.share.service.UserService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "/v1/user")
public class UserController {

    @Resource
    private UserService userService;

    /**
     *
     * 分页查询
     * @param pageDTO
     * @return
     */
    @RequestMapping(value = "/queryPage", method = RequestMethod.POST)
    BaseResult<PageDTO<UserDTO>> queryPage(@RequestBody PageDTO<UserDTO> pageDTO) {
        return userService.queryPage(pageDTO);
    }
}
