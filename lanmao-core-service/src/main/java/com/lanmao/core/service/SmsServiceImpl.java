package com.lanmao.core.service;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.google.common.collect.Maps;
import com.lanmao.common.base.BaseService;
import com.lanmao.common.bean.BaseResult;
import com.lanmao.common.utils.RandomUtils;
import com.lanmao.core.repository.SmsRepository;
import com.lanmao.core.share.dto.SmsDTO;
import com.lanmao.core.share.service.SmsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
public class SmsServiceImpl implements SmsService {

    @Value("${aliyun.oss.accessKey}")
    private String accessKey;

    @Value("${aliyun.oss.accessSecret}")
    private String accessSecret;

    //产品名称:云通信短信API产品,开发者无需替换
    private static final String product = "Dysmsapi";
    //产品域名,开发者无需替换
    private static final String domain = "dysmsapi.aliyuncs.com";

    static {
        //可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

        try {
            DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Resource
    private SmsRepository smsRepository;

    @Override
    public BaseResult<String> sendCode(@RequestParam("mobile") String mobile) {
        try {
            //初始化acsClient,暂不支持region化
            IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKey, accessSecret);
            IAcsClient acsClient = new DefaultAcsClient(profile);

            //组装请求对象-具体描述见控制台-文档部分内容
            SendSmsRequest request = new SendSmsRequest();
            //必填:待发送手机号
            request.setPhoneNumbers(mobile);
            //必填:短信签名-可在短信控制台中找到
            request.setSignName("考拉时光spa");
            //必填:短信模板-可在短信控制台中找到
            request.setTemplateCode("SMS_145205911");
            //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为

            String smsCode = RandomUtils.randomCode();
            Map<String, Object> map = Maps.newHashMap();
            map.put("code", smsCode);
            request.setTemplateParam(JSON.toJSONString(map));

            //hint 此处可能会抛出异常，注意catch
            SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
            log.info(JSON.toJSONString(sendSmsResponse));

            SmsDTO smsDTO = new SmsDTO();
            smsDTO.setMobile(mobile);
            smsDTO.setCode(smsCode);
            smsRepository.save(smsDTO);
        } catch (Exception e) {
            log.info("error", e);
            return BaseResult.fail("发送失败");
        }
        return BaseResult.success("success");
    }
}
