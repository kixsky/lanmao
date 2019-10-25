package com.lanmao.core.service;

import com.alibaba.fastjson.JSON;
import com.lanmao.common.bean.BaseResult;
import com.lanmao.common.bean.PageDTO;
import com.lanmao.core.repository.ProductRepository;
import com.lanmao.core.share.dto.ProductDTO;
import com.lanmao.core.share.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Slf4j
public class ProductServiceImpl implements ProductService {

    @Resource
    private ProductRepository productRepository;

    @Override
    public BaseResult<Long> save(@RequestBody ProductDTO productDTO) {
        BaseResult<Long> baseResult = new BaseResult<>();
        baseResult.setCodeSuccess();
        Long newId = productRepository.save(productDTO);
        baseResult.setData(newId);
        return baseResult;
    }

    @Override
    public BaseResult<List<ProductDTO>> queryList(@RequestBody ProductDTO queryObj) {
        BaseResult<List<ProductDTO>> baseResult = new BaseResult<>();
        baseResult.setCodeSuccess();
        List<ProductDTO> list  = productRepository.queryList(queryObj);
        baseResult.setData(list);
        return baseResult;
    }

    @Override
    public BaseResult<PageDTO<ProductDTO>> queryPage(@RequestBody PageDTO<ProductDTO> pageDTO) {
        log.info("pageDTO: {}", JSON.toJSONString(pageDTO));
        BaseResult<PageDTO<ProductDTO>> baseResult = new BaseResult<>();
        baseResult.setCodeSuccess();
        if (pageDTO.getPage() == null) {
            pageDTO.setPage(1);
        }
        if (pageDTO.getPageSize() == null) {
            pageDTO.setPageSize(10);
        }
        ProductDTO params = pageDTO.getParams();
        if (params == null) {
            params = new ProductDTO();
        }
        final Integer page = pageDTO.getPage();
        final Integer pageSize = pageDTO.getPageSize();
        params.setOffset((page - 1) * pageSize);
        params.setOffset(pageSize);
        List<ProductDTO> list = productRepository.queryList(params);
        final Integer totalCount = productRepository.countQueryList(params);
        pageDTO.setList(list);
        pageDTO.setTotalCount(totalCount);
        baseResult.setData(pageDTO);
        return baseResult;
    }
}
