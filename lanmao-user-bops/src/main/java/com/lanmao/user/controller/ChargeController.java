package com.lanmao.user.controller;

import com.alibaba.fastjson.JSON;
import com.lanmao.common.bean.BaseResult;
import com.lanmao.common.exception.BusinessException;
import com.lanmao.common.utils.CommonUtils;
import com.lanmao.common.utils.RequestUtil;
import com.lanmao.core.share.dto.ChargePackageDTO;
import com.lanmao.core.share.dto.UserChargeRecordDTO;
import com.lanmao.core.share.dto.UserDTO;
import com.lanmao.core.share.service.ChargePackageService;
import com.lanmao.core.share.service.UserService;
import com.lanmao.user.auth.LoginHolder;
import com.lanmao.user.utils.WechatPayParams;
import com.lanmao.user.utils.WechatUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping(value = "/v1/charge")
public class ChargeController {

    @Resource
    private UserService userService;

    @Resource
    private ChargePackageService chargePackageService;

    @Resource
    private WechatUtils wechatUtils;

    /**
     *
     * 充值下单
     * @param bookParams
     * @return
     */
    @RequestMapping(value = "/bookCharge", method = RequestMethod.POST)
    public BaseResult<UserChargeRecordDTO> bookCharge(@RequestBody UserChargeRecordDTO bookParams, HttpServletRequest request) {
        log.info("bookParams: {}", JSON.toJSONString(bookParams));
        BaseResult<UserChargeRecordDTO> baseResult = new BaseResult<>();
        baseResult.setCodeSuccess();
        UserDTO userDTO = LoginHolder.get();
        if (StringUtils.isEmpty(bookParams.getOpenId())) {
            UserDTO queryObj = new UserDTO();
            queryObj.setId(userDTO.getId());
            BaseResult<UserDTO> userResult = userService.queryOne(queryObj);
            if (userResult.failed()) {
                throw new BusinessException(userResult.getMessage());
            }
            UserDTO dbUserDTO = userResult.getData();
            bookParams.setOpenId(dbUserDTO.getOpenId());
        }
        CommonUtils.checkParams(StringUtils.isEmpty(bookParams.getOpenId()), "openId为空");
        CommonUtils.checkParams(bookParams.getPackageId() == null, "套餐未选择");
        final String openId = bookParams.getOpenId();
        final Long packageId = bookParams.getPackageId();

        ChargePackageDTO queryPackageDTO = new ChargePackageDTO();
        queryPackageDTO.setId(bookParams.getPackageId());
        BaseResult<List<ChargePackageDTO>> packageResult = chargePackageService.queryList(queryPackageDTO);
        if (packageResult.failed()) {
            throw new BusinessException(packageResult.getMessage());
        }
        List<ChargePackageDTO> packageList = packageResult.getData();
        if (CollectionUtils.isEmpty(packageList)) {
            throw new BusinessException("套餐Id无效");
        }
        ChargePackageDTO chargePackageDTO = packageList.get(0);
        //先入库
        String tradeNo = CommonUtils.genOrderNo();
        UserChargeRecordDTO userChargeRecordDTO = new UserChargeRecordDTO();
        userChargeRecordDTO.setUserId(userDTO.getId());
        userChargeRecordDTO.setPackageId(packageId);
        userChargeRecordDTO.setTradeNo(tradeNo);
        userChargeRecordDTO.setStatus(1);
        userChargeRecordDTO.setPayAmount(chargePackageDTO.getChargeAmount());
        BaseResult<UserChargeRecordDTO> bookChargeResult = userService.bookCharge(userChargeRecordDTO);
        if (bookChargeResult.failed()) {
            throw new BusinessException(bookChargeResult.getMessage());
        }
        WechatPayParams wechatPayParams = new WechatPayParams();
        wechatPayParams.setBody("测试: " + chargePackageDTO.getPackageName());
        wechatPayParams.setDetail("测试: " + chargePackageDTO.getPackageName());
        wechatPayParams.setOutTradeNo(tradeNo);
        wechatPayParams.setOpenId(openId);
        wechatPayParams.setSpbillCreateIp(RequestUtil.getRemoteIp(request));
        wechatPayParams.setTotalFee(chargePackageDTO.getChargeAmount().multiply(BigDecimal.valueOf(100)).intValue());
        wechatPayParams.setNotifyUrl("http://smapi.yinhuspa.com/v1/charge/notify");
        Map<String, String>  payParams = wechatUtils.preOrder(wechatPayParams);
        userChargeRecordDTO.setPayParams(payParams);
        baseResult.setData(userChargeRecordDTO);
        return baseResult;
    }
}
