package com.lanmao.web.controller;

import com.lanmao.core.share.service.CouponService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "/v1/coupon")
public class CouponController {

    @Resource
    private CouponService couponService;


}
