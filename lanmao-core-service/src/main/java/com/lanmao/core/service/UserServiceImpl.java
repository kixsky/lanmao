package com.lanmao.core.service;

import com.alibaba.fastjson.JSON;
import com.lanmao.common.bean.BaseResult;
import com.lanmao.common.bean.PageDTO;
import com.lanmao.common.constants.ErrorCodeEnum;
import com.lanmao.common.exception.BusinessException;
import com.lanmao.common.utils.CommonUtils;
import com.lanmao.core.repository.*;
import com.lanmao.core.share.dto.*;
import com.lanmao.core.share.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@RestController
@Slf4j
public class UserServiceImpl implements UserService {

    @Resource
    private UserRepository userRepository;

    @Resource
    private SmsRepository smsRepository;

    @Resource
    private UserWalletRepository userWalletRepository;

    @Resource
    private ChargePackageRepository chargePackageRepository;

    @Resource
    private UserChargeRecordRepository userChargeRecordRepository;

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
    public BaseResult<BigDecimal> queryBalance(@RequestBody UserDTO userDTO) {
        BaseResult<BigDecimal> baseResult = new BaseResult<>();
        baseResult.setCodeSuccess();
        CommonUtils.checkParams(userDTO.getId() == null, "用户Id不能为空");
        UserWalletDTO  userWalletDTO = userWalletRepository.getUserWallet(userDTO.getId());
        if (userWalletDTO == null) {
            baseResult.setData(BigDecimal.ZERO);
        } else {
            baseResult.setData(userWalletDTO.getBalance());
        }
        return baseResult;
    }

    @Override
    public BaseResult<List<ChargePackageDTO>> queryChargePackages(@RequestBody ChargePackageDTO chargePackageDTO) {
        BaseResult<List<ChargePackageDTO>> baseResult = new BaseResult<>();
        baseResult.setCodeSuccess();
        List<ChargePackageDTO> list = chargePackageRepository.queryList(chargePackageDTO);
        baseResult.setData(list);
        return baseResult;
    }

    @Override
    public BaseResult<UserChargeRecordDTO> bookCharge(@RequestBody UserChargeRecordDTO bookParams) {
        BaseResult<UserChargeRecordDTO> baseResult = new BaseResult<>();
        baseResult.setCodeSuccess();
        Long newId = userChargeRecordRepository.save(bookParams);
        UserChargeRecordDTO newDTO = userChargeRecordRepository.queryById(newId);
        baseResult.setData(newDTO);
        return baseResult;
    }

    @Override
    public BaseResult<PageDTO<UserDTO>> queryPage(@RequestBody PageDTO<UserDTO> pageDTO) {
        BaseResult<PageDTO<UserDTO>> baseResult = new BaseResult<>();
        baseResult.setCodeSuccess();
        UserDTO params = pageDTO.getParams();
        if (params != null) {
            params = new UserDTO();
            pageDTO.setParams(params);
        }
        pageDTO.setDefaultValue();
        final Integer page = pageDTO.getPage();
        final Integer pageSize = pageDTO.getPageSize();
        final Integer offset = (page - 1) * pageSize;
        params.setOffset(offset);
        params.setLimit(pageSize);
        List<UserDTO> list = userRepository.queryList(params);
        int totalCount = userRepository.countQueryList(params);
        pageDTO.setTotalCount(totalCount);
        pageDTO.setList(list);
        baseResult.setData(pageDTO);
        return baseResult;
    }

    @Override
    @Transactional
    public BaseResult<String> chargeResult(@RequestBody UserChargeRecordDTO chargeResultDTO) {
        BaseResult<String> baseResult = new BaseResult<>();
        baseResult.setCodeSuccess();
        log.info("chargeResultDTO: {}", JSON.toJSONString(chargeResultDTO));
        final String tradeNo = chargeResultDTO.getTradeNo();
        final BigDecimal payAmount = chargeResultDTO.getPayAmount();
        final String outTradeNo = chargeResultDTO.getOutTradeNo();
        final String payBackJson = chargeResultDTO.getPayBackJson();

        UserChargeRecordDTO query = new UserChargeRecordDTO();
        query.setTradeNo(tradeNo);
        List<UserChargeRecordDTO> list = userChargeRecordRepository.queryList(query);
        if (CollectionUtils.isEmpty(list)) {
            throw new BusinessException("没有查到充值记录");
        }
        UserChargeRecordDTO userChargeRecordDTO = list.get(0);
        UserDTO user = userRepository.queryById(userChargeRecordDTO.getUserId());
        UserChargeRecordDTO updateDTO = new UserChargeRecordDTO();
        updateDTO.setId(userChargeRecordDTO.getId());
        updateDTO.setOutTradeNo(outTradeNo);
        updateDTO.setPayBackJson(payBackJson);
        updateDTO.setStatus(2);
        userChargeRecordRepository.updateById(updateDTO);
        ChargePackageDTO chargePackageDTO = chargePackageRepository.queryById(userChargeRecordDTO.getPackageId());
        if (chargePackageDTO == null) {
            throw new BusinessException("套餐不存在");
        }
        BigDecimal totalAddAmount = chargePackageDTO.getChargeAmount().add(chargePackageDTO.getDonationAmount());
        UserWalletDTO queryUserWalletDTO = new UserWalletDTO();
        queryUserWalletDTO.setUserId(user.getId());
        UserWalletDTO userWalletDTO = userWalletRepository.queryOne(queryUserWalletDTO);
        if (userWalletDTO == null) {
            userWalletDTO = new UserWalletDTO();
            userWalletDTO.setUserId(user.getId());
            userWalletDTO.setMobile(user.getMobile());
            userWalletDTO.setBalance(totalAddAmount);
            userWalletRepository.save(userWalletDTO);
        } else {
            final BigDecimal balance = userWalletDTO.getBalance();
            BigDecimal sumBalance = balance.add(totalAddAmount);
            userWalletDTO.setBalance(sumBalance);
            userWalletRepository.updateById(userWalletDTO);
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
