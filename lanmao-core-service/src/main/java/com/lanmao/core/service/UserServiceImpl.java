package com.lanmao.core.service;

import com.lanmao.common.bean.BaseResult;
import com.lanmao.common.constants.ErrorCodeEnum;
import com.lanmao.common.utils.CommonUtils;
import com.lanmao.core.dataobject.UserDO;
import com.lanmao.core.mapper.UserDAO;
import com.lanmao.core.repository.UserRepository;
import com.lanmao.core.share.dto.UserDTO;
import com.lanmao.core.share.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@RestController
@Slf4j
public class UserServiceImpl implements UserService {

    @Resource
    private UserDAO userMapper;

    @Resource
    private UserRepository userRepository;

    @Override
    public BaseResult<UserDTO> queryOne(@RequestBody UserDTO user) {
        BaseResult<UserDTO> result = new BaseResult<>();
        result.setCode(ErrorCodeEnum.CODE_SUCCESS.getCode());
        result.setData(user);
        user.setGmtCreated(new Date());
        return result;
    }

    @Override
    public BaseResult<Integer> updateById(@RequestBody UserDTO updateObj) {
        BaseResult<Integer> baseResult = new BaseResult<>();
        baseResult.setCode(ErrorCodeEnum.CODE_SUCCESS.getCode());
        int updateCount = userRepository.updateById(updateObj);
        baseResult.setData(updateCount);
        return baseResult;
    }

    @Override
    public BaseResult<Integer> deleteById(@RequestBody UserDTO deleteObj) {
        BaseResult<Integer> baseResult = new BaseResult<>();
        baseResult.setCodeSuccess();

        return null;
    }

    @Override
    public BaseResult<List<UserDTO>> queryList(@RequestBody UserDTO queryObj) {
        BaseResult<List<UserDTO>> baseResult = new BaseResult<>();
        baseResult.setCode(ErrorCodeEnum.CODE_SUCCESS.getCode());
        baseResult.setData(userRepository.queryList(queryObj));
        return baseResult;
    }

    @Override
    public BaseResult<Long> save(@RequestBody UserDTO userDTO) {
        BaseResult<Long> baseResult = new BaseResult<>();
        baseResult.setCode(ErrorCodeEnum.CODE_SUCCESS.getCode());
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
