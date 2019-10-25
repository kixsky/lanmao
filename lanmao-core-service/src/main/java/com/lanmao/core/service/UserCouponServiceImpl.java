package com.lanmao.core.service;

import com.lanmao.common.bean.BaseResult;
import com.lanmao.core.repository.UserCouponRepository;
import com.lanmao.core.share.dto.UserCouponDTO;
import com.lanmao.core.share.service.UserCouponService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Slf4j
public class UserCouponServiceImpl implements UserCouponService {

    @Resource
    private UserCouponRepository userCouponRepository;

    @Override
    public BaseResult<Long> save(@RequestBody UserCouponDTO userCouponDTO) {
        return null;
    }

    @Override
    public BaseResult<List<UserCouponDTO>> queryList(@RequestBody UserCouponDTO queryObj) {
        return null;
    }
}
