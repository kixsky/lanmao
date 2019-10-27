package com.lanmao.core.service;

import com.alibaba.fastjson.JSON;
import com.lanmao.common.bean.BaseResult;
import com.lanmao.common.bean.PageDTO;
import com.lanmao.core.repository.OrderApplyRepository;
import com.lanmao.core.share.dto.OrderApplyDTO;
import com.lanmao.core.share.service.OrderApplyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Slf4j
public class OrderApplyServiceImpl implements OrderApplyService {

    @Resource
    private OrderApplyRepository orderApplyRepository;

    @Override
    public BaseResult<Long> save(@RequestBody OrderApplyDTO saveObject) {
        BaseResult<Long> baseResult = new BaseResult<>();
        baseResult.setCodeSuccess();
        Long newId = orderApplyRepository.save(saveObject);
        baseResult.setData(newId);
        return baseResult;
    }

    @Override
    public BaseResult<List<OrderApplyDTO>> queryList(@RequestBody OrderApplyDTO query) {
        BaseResult<List<OrderApplyDTO>> baseResult = new BaseResult<>();
        baseResult.setCodeSuccess();
        List<OrderApplyDTO> list = orderApplyRepository.queryList(query);
        baseResult.setData(list);
        return baseResult;
    }

    @Override
    public BaseResult<PageDTO<OrderApplyDTO>> queryPage(@RequestBody PageDTO<OrderApplyDTO> pageDTO) {
        log.info("queryPage: {}", JSON.toJSONString(pageDTO));
        BaseResult<PageDTO<OrderApplyDTO>> baseResult = new BaseResult<>();
        baseResult.setCodeSuccess();
        OrderApplyDTO params = pageDTO.getParams();
        if (params == null) {
            params = new OrderApplyDTO();
            pageDTO.setParams(params);
        }
        if (pageDTO.getPage() == null) {
            pageDTO.setPage(1);
        }
        if (pageDTO.getPageSize() == null) {
            pageDTO.setPageSize(10);
        }
        final Integer page = pageDTO.getPage();
        final Integer pageSize = pageDTO.getPageSize();
        final Integer offset = (page - 1) * pageSize;
        params.setOffset(offset);
        params.setLimit(pageSize);
        List<OrderApplyDTO> list = orderApplyRepository.queryList(params);
        int totalCount = orderApplyRepository.countQueryList(params);
        pageDTO.setList(list);
        pageDTO.setTotalCount(totalCount);
        baseResult.setData(pageDTO);
        return baseResult;
    }
}
