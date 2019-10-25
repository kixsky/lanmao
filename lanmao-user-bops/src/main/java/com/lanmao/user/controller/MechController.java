package com.lanmao.user.controller;

import com.lanmao.common.bean.BaseResult;
import com.lanmao.common.bean.PageDTO;
import com.lanmao.core.share.dto.MechDTO;
import com.lanmao.core.share.service.MechService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
@RequestMapping(value = "/v1/mech")
public class MechController {

    @Resource
    private MechService mechService;

    /**
     *
     * 查询技师
     * @param pageDTO
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public BaseResult<PageDTO<MechDTO>> list(@RequestBody PageDTO<MechDTO> pageDTO) {
        return mechService.queryPage(pageDTO);
    }
}
