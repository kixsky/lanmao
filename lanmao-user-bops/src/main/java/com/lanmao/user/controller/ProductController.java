package com.lanmao.user.controller;

import com.lanmao.common.annotation.IgnorePath;
import com.lanmao.common.bean.BaseResult;
import com.lanmao.common.bean.PageDTO;
import com.lanmao.core.share.dto.ProductDTO;
import com.lanmao.core.share.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

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
    @IgnorePath
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public BaseResult<PageDTO<ProductDTO>> list(@RequestBody PageDTO<ProductDTO> queryParams) {
        return productService.queryPage(queryParams);
    }

    /**
     *
     * 查询项目
     * @param productId
     * @return
     */
    @RequestMapping(value = "/{productId}", method = RequestMethod.GET)
    public BaseResult<ProductDTO> queryById(@PathVariable("productId") Long productId) {
        ProductDTO query = new ProductDTO();
        query.setId(productId);
        return productService.queryOne(query);
    }
}
