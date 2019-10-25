package com.lanmao.core.service;

import com.alibaba.fastjson.JSON;
import com.lanmao.common.bean.BaseResult;
import com.lanmao.common.bean.PageDTO;
import com.lanmao.common.exception.BusinessException;
import com.lanmao.core.repository.MechRepository;
import com.lanmao.core.share.dto.MechDTO;
import com.lanmao.core.share.service.MechService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Slf4j
public class MechServiceImpl implements MechService {

    @Resource
    private MechRepository mechRepository;

    @Override
    public BaseResult<PageDTO<MechDTO>> queryPage(@RequestBody PageDTO<MechDTO> pageDTO) {
        log.info("pageDTO: {}", JSON.toJSONString(pageDTO));
        BaseResult<PageDTO<MechDTO>> baseResult = new BaseResult<>();
        baseResult.setCodeSuccess();
        if (pageDTO == null) {
            throw new BusinessException("查询对象不能为空");
        }
        if (pageDTO.getPage() == null) {
            pageDTO.setPage(1);
        }
        if (pageDTO.getPageSize() == null) {
            pageDTO.setPageSize(10);
        }
        MechDTO params = pageDTO.getParams();
        if (params == null) {
            params = new MechDTO();
            pageDTO.setParams(params);
        }
        final Integer offset = (pageDTO.getPage() - 1) * pageDTO.getPageSize();
        final Integer limit = pageDTO.getPageSize();
        params.setOffset(offset);
        params.setLimit(limit);
        List<MechDTO> list = mechRepository.queryList(params);
        int totalCount = mechRepository.countQueryList(params);
        pageDTO.setList(list);
        pageDTO.setTotalCount(totalCount);
        baseResult.setData(pageDTO);
        return baseResult;
    }
}
