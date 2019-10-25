package com.lanmao.core.share.service;

import com.lanmao.common.bean.BaseResult;
import com.lanmao.common.bean.PageDTO;
import com.lanmao.core.share.constants.Constants;
import com.lanmao.core.share.dto.MechDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = Constants.SERVICE_NAME)
@RequestMapping(value = "/api/mech")
public interface MechService {


    /**
     *
     * 查询技师
     * @param pageDTO
     * @return
     */
    @RequestMapping(value = "/queryPage", method = RequestMethod.POST)
    BaseResult<PageDTO<MechDTO>> queryPage(@RequestBody PageDTO<MechDTO> pageDTO);
}
