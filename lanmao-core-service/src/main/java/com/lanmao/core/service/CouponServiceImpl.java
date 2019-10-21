package com.lanmao.core.service;

import com.lanmao.common.base.BaseService;
import com.lanmao.common.bean.BaseResult;
import com.lanmao.common.constants.ErrorCodeEnum;
import com.lanmao.core.repository.CouponRepository;
import com.lanmao.core.share.dto.CouponDTO;
import com.lanmao.core.share.service.CouponService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Slf4j
public class CouponServiceImpl extends BaseService<CouponDTO> implements CouponService {

    @Resource
    private CouponRepository couponRepository;

    @Override
    public BaseResult<Long> save(@RequestBody CouponDTO couponDTO) {
        BaseResult<Long> baseResult = new BaseResult<>();
        baseResult.setCodeSuccess();
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
        return null;
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
}
