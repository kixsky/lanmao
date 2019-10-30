package com.lanmao.core.service;

import com.lanmao.common.bean.BaseResult;
import com.lanmao.common.bean.PageDTO;
import com.lanmao.common.constants.CouponRuleEnum;
import com.lanmao.common.constants.CouponTypeEnum;
import com.lanmao.common.constants.ErrorCodeEnum;
import com.lanmao.common.utils.CommonUtils;
import com.lanmao.core.repository.CouponRepository;
import com.lanmao.core.share.dto.CouponDTO;
import com.lanmao.core.share.service.CouponService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Slf4j
public class CouponServiceImpl implements CouponService {

    @Resource
    private CouponRepository couponRepository;

    @Override
    public BaseResult<Long> save(@RequestBody CouponDTO couponDTO) {
        BaseResult<Long> baseResult = new BaseResult<>();
        baseResult.setCodeSuccess();
        CommonUtils.checkParams(StringUtils.isEmpty(couponDTO.getName()), "名称不能为空");
        CommonUtils.checkParams(StringUtils.isEmpty(couponDTO.getRuleCode()), "ruleCode不能为空");
        CommonUtils.checkParams(StringUtils.isEmpty(couponDTO.getRuleName()), "ruleName不能为空");
        CommonUtils.checkParams(couponDTO.getEffectiveDate() == null, "有效期起期不能为空");
        CommonUtils.checkParams(couponDTO.getExpiryDate() == null, "有效期止期不能为空");
        CommonUtils.checkParams(couponDTO.getDiscount() == null, "优惠金额不能为空");
        CommonUtils.checkParams(couponDTO.getType() == null, "type不能为空");
        CommonUtils.checkParams(CouponTypeEnum.getByCode(couponDTO.getType()) == null, "type不合法");
        CommonUtils.checkParams(CouponRuleEnum.getByCode(couponDTO.getRuleCode()) == null, "ruleCode不合法");

        try {
            Long newId = couponRepository.save(couponDTO);
            baseResult.setData(newId);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            baseResult.setCode(ErrorCodeEnum.CODE_FAIL.getCode());
            baseResult.setMessage(e.getMessage());
        }
        return baseResult;
    }

    @Override
    public BaseResult<List<CouponDTO>> queryList(@RequestBody CouponDTO queryObj) {
        BaseResult<List<CouponDTO>> baseResult = new BaseResult<>();
        baseResult.setCodeSuccess();
        List<CouponDTO> list = couponRepository.queryList(queryObj);
        baseResult.setData(list);
        return baseResult;
    }

    @Override
    public BaseResult<CouponDTO> queryOne(@RequestBody CouponDTO queryObj) {
        return null;
    }

    @Override
    public BaseResult<Integer> updateById(@RequestBody CouponDTO updateObj) {
        return null;
    }

    @Override
    public BaseResult<Integer> deleteById(@RequestBody CouponDTO deleteObj) {
        return null;
    }

    @Override
    public BaseResult<PageDTO<CouponDTO>> queryPage(@RequestBody PageDTO<CouponDTO> pageDTO) {
        BaseResult<PageDTO<CouponDTO>> baseResult = new BaseResult<>();
        baseResult.setCodeSuccess();
        baseResult.setData(couponRepository.queryPage(pageDTO));
        return baseResult;
    }
}
