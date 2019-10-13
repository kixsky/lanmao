package com.lanmao.core.share.service;

import com.lanmao.common.bean.BaseResult;
import com.lanmao.core.share.constants.Constants;
import com.lanmao.core.share.dto.CouponDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = Constants.SERVICE_NAME)
@RequestMapping(value = "/api/coupon")
public interface CouponService {


    /**
     *
     * 新增优惠券
     * @param couponDTO
     * @return
     */
    @RequestMapping(value = "/addCoupon",method = RequestMethod.POST)
    BaseResult<String> addCoupon(@RequestBody CouponDTO couponDTO);
}
