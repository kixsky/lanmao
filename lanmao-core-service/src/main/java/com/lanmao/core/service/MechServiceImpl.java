package com.lanmao.core.service;

import com.alibaba.fastjson.JSON;
import com.lanmao.common.bean.BaseResult;
import com.lanmao.common.bean.PageDTO;
import com.lanmao.common.exception.BusinessException;
import com.lanmao.common.utils.CommonUtils;
import com.lanmao.core.repository.MechProductRepository;
import com.lanmao.core.repository.MechRepository;
import com.lanmao.core.repository.SmsRepository;
import com.lanmao.core.share.dto.MechDTO;
import com.lanmao.core.share.dto.ProductDTO;
import com.lanmao.core.share.service.MechService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Slf4j
public class MechServiceImpl implements MechService {

    @Resource
    private MechRepository mechRepository;

    @Resource
    private MechProductRepository mechProductRepository;

    @Resource
    private SmsRepository smsRepository;

    @Override
    public BaseResult<Long> save(@RequestBody MechDTO mechDTO) {
        BaseResult<Long> baseResult = new BaseResult<>();
        baseResult.setCodeSuccess();
        Long newId = mechRepository.save(mechDTO);
        baseResult.setData(newId);
        return baseResult;
    }

    @Override
    public BaseResult<PageDTO<MechDTO>> queryPage(@RequestBody PageDTO<MechDTO> pageDTO) {
        log.info("pageDTO: {}", JSON.toJSONString(pageDTO));
        BaseResult<PageDTO<MechDTO>> baseResult = new BaseResult<>();
        baseResult.setCodeSuccess();
        if (pageDTO == null) {
            throw new BusinessException("查询对象不能为空");
        }
        if (pageDTO.getPage() == null) {
            pageDTO.setPage(1);
        }
        if (pageDTO.getPageSize() == null) {
            pageDTO.setPageSize(10);
        }
        MechDTO params = pageDTO.getParams();
        if (params == null) {
            params = new MechDTO();
            pageDTO.setParams(params);
        }
        final Integer offset = (pageDTO.getPage() - 1) * pageDTO.getPageSize();
        final Integer limit = pageDTO.getPageSize();
        params.setOffset(offset);
        params.setLimit(limit);
        List<MechDTO> list = mechRepository.queryList(params);
        int totalCount = mechRepository.countQueryList(params);
        pageDTO.setList(list);
        pageDTO.setTotalCount(totalCount);
        baseResult.setData(pageDTO);
        return baseResult;
    }

    @Override
    public BaseResult<List<ProductDTO>> queryProduct(@RequestParam("mechId") Long mechId) {
        BaseResult<List<ProductDTO>> baseResult = new BaseResult<>();
        baseResult.setCodeSuccess();
        List<ProductDTO> list = mechProductRepository.queryMechProduct(mechId);
        baseResult.setData(list);
        return baseResult;
    }

    @Override
    public BaseResult<MechDTO> login(@RequestBody MechDTO loginDTO) {
        log.info("loginDTO: {}", JSON.toJSONString(loginDTO));
        BaseResult<MechDTO> baseResult = new BaseResult<>();
        baseResult.setCodeSuccess();
        final String mobile = loginDTO.getMobile();
        final String smsCode = loginDTO.getSmsCode();
        CommonUtils.checkParams(StringUtils.isEmpty(mobile), "手机号不能为空");
        CommonUtils.checkParams(StringUtils.isEmpty(smsCode), "验证码不能为空");
        MechDTO query = new MechDTO();
        query.setMobile(mobile);
        boolean isValid = smsRepository.checkSms(mobile, smsCode);
        if (!isValid) {
            throw new BusinessException("验证码不正确");
        }
        MechDTO mechDTO = mechRepository.queryOne(query);
        if (mechDTO == null) {
            throw new BusinessException("用户不存在");
        }
        baseResult.setData(mechDTO);
        return baseResult;
    }

    @Override
    public BaseResult<MechDTO> queryOne(@RequestBody MechDTO query) {
        BaseResult<MechDTO> baseResult = new BaseResult<>();
        baseResult.setCodeSuccess();
        MechDTO mechDTO = mechRepository.queryOne(query);
        baseResult.setData(mechDTO);
        return baseResult;
    }
}
