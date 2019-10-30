package com.lanmao.web.controller;

import com.lanmao.common.bean.BaseResult;
import com.lanmao.common.bean.PageDTO;
import com.lanmao.core.share.dto.CouponDTO;
import com.lanmao.core.share.service.CouponService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "/v1/coupon")
public class CouponController {

    @Resource
    private CouponService couponService;

    /**
     * 新增
     * @param couponDTO
     * @return
     */
    @RequestMapping(value = "/addCoupon", method = RequestMethod.POST)
    public BaseResult<?> addCoupon(@RequestBody CouponDTO couponDTO) {
        return couponService.save(couponDTO);
    }


    /**
     *
     * 分页查询
     * @param pageDTO
     * @return
     */
    @RequestMapping(value = "/queryPage", method = RequestMethod.POST)
    public BaseResult<PageDTO<CouponDTO>> queryPage(@RequestBody PageDTO<CouponDTO> pageDTO) {
        return couponService.queryPage(pageDTO);
    }
}
