package com.lanmao.core.share.service;

import com.lanmao.common.bean.BaseResult;
import com.lanmao.common.bean.PageDTO;
import com.lanmao.core.share.constants.Constants;
import com.lanmao.core.share.dto.ProductDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(value = Constants.SERVICE_NAME)
@RequestMapping(value = "/api/product")
public interface ProductService {

    /**
     *
     * 保存
     * @param productDTO
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    BaseResult<Long> save(@RequestBody ProductDTO productDTO);


    /**
     *
     * 查询列表
     * @param queryObj
     * @return
     */
    @RequestMapping(value = "/queryList", method = RequestMethod.POST)
    BaseResult<List<ProductDTO>> queryList(@RequestBody ProductDTO queryObj);


    /**
     *
     * 分页查询
     * @param queryParams
     * @return
     */
    @RequestMapping(value = "/queryPage", method = RequestMethod.POST)
    BaseResult<PageDTO<ProductDTO>> queryPage(@RequestBody PageDTO<ProductDTO> queryParams);

}
