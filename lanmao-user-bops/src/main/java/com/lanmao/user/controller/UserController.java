package com.lanmao.user.controller;

import com.lanmao.common.annotation.IgnorePath;
import com.lanmao.common.bean.BaseResult;
import com.lanmao.core.share.dto.ChargePackageDTO;
import com.lanmao.core.share.dto.UserDTO;
import com.lanmao.core.share.service.UserService;
import com.lanmao.user.auth.LoginHolder;
import com.lanmao.user.utils.WechatUtils;
import com.lanmao.user.utils.WxAuthInfo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping(value = "/v1/user")
public class UserController {

    @Resource
    private UserService userService;

    @Resource
    private WechatUtils wechatUtils;

    /**
     *
     * 获取用户信息
     * @return
     */
    @RequestMapping(value = "/getUserInfo")
    public BaseResult<UserDTO> getUser() {
        BaseResult<UserDTO> baseResult = new BaseResult<>();
        baseResult.setCodeSuccess();
        baseResult.setData(LoginHolder.get());
        return baseResult;
    }


    /**
     *
     * 查询余额
     * @return
     */
    @RequestMapping(value = "/getBalance")
    public BaseResult<BigDecimal> getBalance() {
        return userService.queryBalance(LoginHolder.get());
    }


    /**
     *
     * 查询充值套餐
     * @return
     */
    @RequestMapping(value = "/getChargePackages")
    public BaseResult<List<ChargePackageDTO>> getChargePackages() {
        return userService.queryChargePackages(new ChargePackageDTO());
    }


    /**
     *
     * 获取用户OpenId
     * @return
     */
    @IgnorePath
    @RequestMapping(value = "/getOpenId", method = RequestMethod.GET)
    public BaseResult<WxAuthInfo> getOpenId(@RequestParam("code") String code) {
        BaseResult<WxAuthInfo> baseResult = new BaseResult<>();
        baseResult.setCodeSuccess();
        baseResult.setData(wechatUtils.getWxAuthInfo(code));
        return baseResult;
    }




}
