package com.lanmao.core.service;

import com.lanmao.common.bean.BaseResult;
import com.lanmao.core.share.dto.CouponDTO;
import com.lanmao.core.share.service.CouponService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class CouponServiceImpl implements CouponService {


    @Override
    public BaseResult<String> addCoupon(@RequestBody CouponDTO couponDTO) {
        return null;
    }
}
