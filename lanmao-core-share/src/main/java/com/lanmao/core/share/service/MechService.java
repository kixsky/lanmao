package com.lanmao.core.share.service;

import com.lanmao.common.bean.BaseResult;
import com.lanmao.common.bean.PageDTO;
import com.lanmao.core.share.constants.Constants;
import com.lanmao.core.share.dto.MechDTO;
import com.lanmao.core.share.dto.ProductDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = Constants.SERVICE_NAME)
@RequestMapping(value = "/api/mech")
public interface MechService {


    /**
     *
     * 保存技师
     * @param mechDTO
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    BaseResult<Long> save(@RequestBody MechDTO mechDTO);

    /**
     *
     * 查询技师
     * @param pageDTO
     * @return
     */
    @RequestMapping(value = "/queryPage", method = RequestMethod.POST)
    BaseResult<PageDTO<MechDTO>> queryPage(@RequestBody PageDTO<MechDTO> pageDTO);


    /**
     *
     * 查询技师能服务的项目
     * @param mechId
     * @return
     */
    @RequestMapping(value = "/queryProduct", method = RequestMethod.POST)
    BaseResult<List<ProductDTO>> queryProduct(@RequestParam("mechId") Long mechId);


    /**
     *
     * 技师登陆
     * @param loginDTO
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    BaseResult<MechDTO> login(@RequestBody MechDTO loginDTO);
}
