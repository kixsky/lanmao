package com.lanmao.user.controller;

import com.alibaba.fastjson.JSON;
import com.lanmao.common.annotation.IgnorePath;
import com.lanmao.common.bean.BaseResult;
import com.lanmao.core.share.dto.UserChargeRecordDTO;
import com.lanmao.core.share.service.ChargePackageService;
import com.lanmao.core.share.service.UserService;
import com.lanmao.user.utils.WechatUtils;
import com.lanmao.user.utils.XmlUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping(value = "/v1/notify")
public class NotifyController {

    @Resource
    private ChargePackageService chargePackageService;

    @Resource
    private WechatUtils wechatUtils;

    @Resource
    private UserService userService;

    @IgnorePath
    @RequestMapping(value = "/chargeResult", method = RequestMethod.POST)
    public void chargeResult(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String xml = IOUtils.toString(request.getReader());
        log.info("微信参数: {}", xml);
        Map<String, String> map = XmlUtils.xmlToMap(xml);
        if (!wechatUtils.validateSign(map)) {
            log.info("微信支付验签未通过: {}", JSON.toJSONString(map));
            throw new IllegalArgumentException("");
        }
        String outTradeNo = map.get("out_trade_no");
        String totalFee = map.get("total_fee");

        UserChargeRecordDTO userChargeRecordDTO = new UserChargeRecordDTO();
        userChargeRecordDTO.setTradeNo(outTradeNo);
        userChargeRecordDTO.setPayBackJson(JSON.toJSONString(map));
        userChargeRecordDTO.setPayAmount(new BigDecimal(totalFee).divide(BigDecimal.valueOf(100)));
        BaseResult<String> chargeResult = userService.chargeResult(userChargeRecordDTO);
        if (chargeResult.failed()) {
            //保存下错误等待人工处理

        }
        Map<String, String> resultMap = new HashMap<>();
        resultMap.put("return_code", "SUCCESS");
        resultMap.put("return_msg", "OK");

        PrintWriter out = response.getWriter();
        response.setContentType("text/plain");
        out.print(XmlUtils.mapToXml(resultMap));
    }
}
