package com.lanmao.user.utils;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Slf4j
@Component
public class WechatUtils {

    @Value("${mp.app.id}")
    private String mpAppId;

    @Value("${mp.app.secret}")
    private String mpAppSecret;

    @Value(("${pay.mch.id}"))
    private String payMchId;

    @Value("${pay.api.key}")
    private String payApiKey;

    /**
     *
     * 获取
     * @param jsCode
     * @return
     */
    public WxAuthInfo getWxAuthInfo(String jsCode) {

        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=%s&secret=%s&code=%s&grant_type=authorization_code";
        String enUrl = String.format(url, mpAppId, mpAppSecret, jsCode);
        try {
            URL url2 = new URL(enUrl);
            String content = IOUtils.toString(url2);
            log.info("getWxAuthInfo: {}", content);
            WxAuthInfo wxAuthInfo = JSON.parseObject(content, WxAuthInfo.class);
            return wxAuthInfo;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static String createNoncestr(int length) {
        String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            Random rd = new Random();
            builder.append(chars.charAt(rd.nextInt(chars.length() - 1)));
        }

        return builder.toString();
    }

    public static String sign(Map<String, String> params, String jsApiKey) {
        log.info("sign: {}", JSON.toJSONString(params));

        List<String> kvs = getSignKeyArray(params);
        kvs.add("key=" + jsApiKey);

        String s = StringUtils.join(kvs, "&");
        return DigestUtils.md5Hex(s).toUpperCase();
    }

    private static List<String> getSignKeyArray(Map<String, String> params) {
        List<String> kvs = new ArrayList<>();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            if (key.equalsIgnoreCase("sign") || StringUtils.isBlank(value)) {
                continue;
            }
            kvs.add(key + "=" + value);
        }
        Collections.sort(kvs);
        return kvs;
    }

    /**
     *
     * 微信预下单
     * @param payParams
     */
    public Map<String, String> preOrder(WechatPayParams payParams) {

        Map<String, String> preOrderMap = new HashMap<>();
        preOrderMap.put("appid", mpAppId);
        preOrderMap.put("mch_id", payMchId);
        preOrderMap.put("nonce_str", createNoncestr(32));
        preOrderMap.put("body", payParams.getBody());
        preOrderMap.put("detail", payParams.getDetail());
        preOrderMap.put("out_trade_no", payParams.getOutTradeNo());
        preOrderMap.put("total_fee", String.valueOf(payParams.getTotalFee()));
        preOrderMap.put("spbill_create_ip", payParams.getSpbillCreateIp());
        preOrderMap.put("notify_url", payParams.getNotifyUrl());
        preOrderMap.put("trade_type", "MWEB");
        preOrderMap.put("openid", payParams.getOpenId());
        preOrderMap.put("sign", sign(preOrderMap, payApiKey));
        log.info("preOrderMap: {}", JSON.toJSONString(preOrderMap));

        HttpPost httpPost = new HttpPost("https://api.mch.weixin.qq.com/pay/unifiedorder");
        httpPost.addHeader(HTTP.CONTENT_TYPE, "application/xml");
        StringEntity entity = new StringEntity(XmlUtils.mapToXml(preOrderMap), "UTF-8");
        entity.setContentType("application/xml");
        entity.setContentEncoding("UTF-8");
        httpPost.setEntity(entity);

        try {
            HttpClient httpClient = HttpClients.createDefault();
            HttpResponse response = httpClient.execute(httpPost);

            String stringEntity = EntityUtils.toString(response.getEntity(), "UTF-8");
            log.info("stringEntity: {}", stringEntity);

            Map<String, String> params = XmlUtils.xmlToMap(stringEntity);

            String return_code = params.get("return_code");
            String result_code = params.get("result_code");

            if (StringUtils.isNotBlank(result_code)
                    && StringUtils.isNotBlank(result_code)
                    && "SUCCESS".equalsIgnoreCase(result_code)
                    && "SUCCESS".equalsIgnoreCase(return_code)) {

                Map<String, String> result = new HashMap<>();
                result.put("appid", mpAppId);
                result.put("package", "prepay_id=" + params.get("prepay_id"));
                result.put("nonceStr", createNoncestr(32));
                result.put("timeStamp", String.valueOf(System.currentTimeMillis()).substring(0, 10));
                result.put("signType", "MD5");
                result.put("paySign", sign(result, payApiKey));

                log.info("result: {}", JSON.toJSONString(result));
                return result;
            }
        } catch (Exception e) {
            log.info("微信预下单失败", e);
        }
        return null;
    }


    public boolean validateSign(Map<String, String> params) {
        String returnedSign = params.get("sign");
        String generatedSign = sign(params, payApiKey);
        return generatedSign.equals(returnedSign);
    }
}
