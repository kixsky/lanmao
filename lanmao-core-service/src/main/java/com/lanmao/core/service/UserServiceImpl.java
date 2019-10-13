package com.lanmao.core.service;

import com.lanmao.common.bean.BaseResult;
import com.lanmao.common.bean.ErrorEnum;
import com.lanmao.common.utils.CommonUtils;
import com.lanmao.core.dataobject.UserDO;
import com.lanmao.core.mapper.UserMapper;
import com.lanmao.core.share.dto.UserDTO;
import com.lanmao.core.share.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

@RestController
@Slf4j
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public BaseResult<UserDTO> query(@RequestBody UserDTO user) {
        BaseResult<UserDTO> result = new BaseResult<>();
        result.setCode(ErrorEnum.SUCCESS.getCode());
        result.setData(user);

        user.setGmtCreated(new Date());
        return result;
    }

    @Override
    public BaseResult<Long> addUser(@RequestBody UserDTO userDTO) {
        BaseResult<Long> baseResult = new BaseResult<>();
        baseResult.setCode(ErrorEnum.SUCCESS.getCode());
        try {
            UserDO userDO = new UserDO();
            BeanUtils.copyProperties(userDTO,userDO);
            CommonUtils.setDefaultValue(userDO);
            userMapper.insert(userDO);
            baseResult.setData(userDO.getId());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return baseResult;
    }
}
