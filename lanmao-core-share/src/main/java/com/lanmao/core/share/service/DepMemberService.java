package com.lanmao.core.share.service;

import com.lanmao.common.base.BaseService;
import com.lanmao.common.bean.BaseResult;
import com.lanmao.core.share.constants.Constants;
import com.lanmao.core.share.dto.DepMemberDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = Constants.SERVICE_NAME)
@RequestMapping(value = "/api/depMember")
public interface DepMemberService extends BaseService<DepMemberDTO> {


    /**
     *
     * 登陆
     * @param depMemberDTO
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    BaseResult<String> login(@RequestBody DepMemberDTO depMemberDTO);
}