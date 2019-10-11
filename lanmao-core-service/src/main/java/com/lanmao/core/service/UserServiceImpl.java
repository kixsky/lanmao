package com.lanmao.core.service;

import com.lanmao.common.bean.BaseResult;
import com.lanmao.common.bean.ErrorEnum;
import com.lanmao.core.share.dto.UserDTO;
import com.lanmao.core.share.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@Slf4j
public class UserServiceImpl implements UserService {


    @Override
    public BaseResult<UserDTO> query(@RequestBody UserDTO user) {
        BaseResult<UserDTO> result = new BaseResult<>();
        result.setCode(ErrorEnum.SUCCESS.getCode());
        result.setData(user);
        user.setGmtCreated(new Date());
        return result;
    }
}
