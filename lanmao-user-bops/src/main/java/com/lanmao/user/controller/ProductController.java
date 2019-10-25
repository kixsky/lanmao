package com.lanmao.user.controller;

import com.lanmao.common.bean.BaseResult;
import com.lanmao.common.bean.PageDTO;
import com.lanmao.core.share.dto.ProductDTO;
import com.lanmao.core.share.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
@RequestMapping(value = "/v1/product")
public class ProductController {

    @Resource
    private ProductService productService;

    /**
     *
     * 分页查询产品
     * @param queryParams
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public BaseResult<PageDTO<ProductDTO>> list(@RequestBody PageDTO<ProductDTO> queryParams) {
        return productService.queryPage(queryParams);
    }
}
