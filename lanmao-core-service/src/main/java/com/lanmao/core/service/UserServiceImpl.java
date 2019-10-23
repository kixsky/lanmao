package com.lanmao.core.service;

import com.alibaba.fastjson.JSON;
import com.lanmao.common.base.BaseService;
import com.lanmao.common.bean.BaseResult;
import com.lanmao.common.constants.ErrorCodeEnum;
import com.lanmao.common.exception.BusinessException;
import com.lanmao.common.utils.CommonUtils;
import com.lanmao.core.dataobject.UserDO;
import com.lanmao.core.mapper.UserDAO;
import com.lanmao.core.repository.SmsRepository;
import com.lanmao.core.repository.UserRepository;
import com.lanmao.core.share.dto.LoginDTO;
import com.lanmao.core.share.dto.UserDTO;
import com.lanmao.core.share.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@RestController
@Slf4j
public class UserServiceImpl extends BaseService<UserDTO> implements UserService {

    @Resource
    private UserRepository userRepository;

    @Resource
    private SmsRepository smsRepository;

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
    public BaseResult<UserDTO> login(@RequestBody LoginDTO loginDTO) {
        log.info("login: {}", JSON.toJSONString(loginDTO));
        BaseResult<UserDTO> baseResult = new BaseResult<>();
        baseResult.setCodeSuccess();
        try {
            CommonUtils.checkParams(StringUtils.isEmpty(loginDTO.getMobile()),
                    "手机号不能为空");
            CommonUtils.checkParams(StringUtils.isEmpty(loginDTO.getSmsCode()),
                    "验证码不能为空");
            //验证码验证
            boolean checkSms = smsRepository.checkSms(loginDTO.getMobile(), loginDTO.getSmsCode());
            if (!checkSms) {
                throw new BusinessException("验证码不正确");
            }
            UserDTO queryDTO = new UserDTO();
            queryDTO.setMobile(loginDTO.getMobile());
            UserDTO loginUserDTO = userRepository.queryOne(queryDTO);
            if (loginUserDTO == null) {
                //当前手机号未注册,先注册
                UserDTO newUser = new UserDTO();
                newUser.setName(loginDTO.getMobile());
                newUser.setMobile(loginDTO.getMobile());
                Long newUserId = userRepository.save(newUser);
                loginUserDTO = userRepository.queryOne(queryDTO);
                baseResult.setData(loginUserDTO);
            } else {
                baseResult.setData(loginUserDTO);
            }
        } catch (BusinessException e) {
            baseResult.setCode(e.getCode());
            baseResult.setMessage(e.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            baseResult.setCode(ErrorCodeEnum.CODE_FAIL.getCode());
            baseResult.setMessage(e.getMessage());
        }
        return baseResult;
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
            Long newId = userRepository.save(userDTO);
            baseResult.setData(newId);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return baseResult;
    }
}
