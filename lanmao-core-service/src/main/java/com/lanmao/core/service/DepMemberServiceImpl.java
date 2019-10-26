package com.lanmao.core.service;

import com.alibaba.fastjson.JSON;
import com.lanmao.common.bean.BaseResult;
import com.lanmao.common.constants.CommonConstants;
import com.lanmao.common.exception.BusinessException;
import com.lanmao.common.utils.CommonUtils;
import com.lanmao.common.utils.MD5Utils;
import com.lanmao.core.repository.DepMemberRepository;
import com.lanmao.core.share.dto.DepMemberDTO;
import com.lanmao.core.share.service.DepMemberService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Slf4j
public class DepMemberServiceImpl implements DepMemberService {

    @Resource
    private DepMemberRepository depMemberRepository;

    @Override
    public BaseResult<DepMemberDTO> login(@RequestBody DepMemberDTO loginDTO) {
        log.info("login: {}", JSON.toJSONString(loginDTO));
        BaseResult<DepMemberDTO> baseResult = new BaseResult<>();
        baseResult.setCodeSuccess();
        final String loginName = loginDTO.getLoginName();
        final String password = loginDTO.getPassword();
        CommonUtils.checkParams(StringUtils.isEmpty(loginName), "loginName不能为空");
        CommonUtils.checkParams(StringUtils.isEmpty(password), "password不能为空");
        DepMemberDTO queryDTO = new DepMemberDTO();
        queryDTO.setLoginName(loginName);
        DepMemberDTO depMemberDTO = depMemberRepository.queryOne(queryDTO);
        String md5Password = MD5Utils.getMD5(password);
        if (!md5Password.equals(depMemberDTO.getPassword())) {
            throw new BusinessException("密码不正确");
        }
        baseResult.setData(depMemberDTO);
        return baseResult;
    }

    @Override
    public BaseResult<Long> save(@RequestBody DepMemberDTO saveObject) {
        return null;
    }

    @Override
    public BaseResult<List<DepMemberDTO>> queryList(@RequestBody DepMemberDTO queryObj) {
        return null;
    }

    @Override
    public BaseResult<DepMemberDTO> queryOne(@RequestBody DepMemberDTO queryObj) {
        return null;
    }

    @Override
    public BaseResult<Integer> updateById(@RequestBody DepMemberDTO updateObj) {
        return null;
    }

    @Override
    public BaseResult<Integer> deleteById(@RequestBody DepMemberDTO deleteObj) {
        return null;
    }
}
