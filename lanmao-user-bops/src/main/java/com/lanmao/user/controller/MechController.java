package com.lanmao.user.controller;

import com.lanmao.common.annotation.IgnorePath;
import com.lanmao.common.bean.BaseResult;
import com.lanmao.common.bean.PageDTO;
import com.lanmao.core.share.dto.MechDTO;
import com.lanmao.core.share.dto.ProductDTO;
import com.lanmao.core.share.service.MechService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Slf4j
@RequestMapping(value = "/v1/mech")
public class MechController {

    @Resource
    private MechService mechService;

    /**
     *
     * 根据Id查询
     * @param mechId
     * @return
     */
    @RequestMapping(value = "/{mechId}", method = RequestMethod.GET)
    public BaseResult<MechDTO> queryById(@PathVariable("mechId") Long mechId) {
        MechDTO query = new MechDTO();
        query.setId(mechId);
        return mechService.queryOne(query);
    }

    /**
     *
     * 查询技师
     * @param pageDTO
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @IgnorePath
    public BaseResult<PageDTO<MechDTO>> list(@RequestBody PageDTO<MechDTO> pageDTO) {
        return mechService.queryPage(pageDTO);
    }

    /**
     *
     * 查询技师项目
     * @param mechDTO
     * @return
     */
    @RequestMapping(value = "/selectProduct", method = RequestMethod.POST)
    @IgnorePath
    public BaseResult<List<ProductDTO>> selectProduct(@RequestBody MechDTO mechDTO) {
        return mechService.queryProduct(mechDTO.getId());
    }



}
