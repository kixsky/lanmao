package com.lanmao.core.service;

import com.lanmao.common.base.BaseService;
import com.lanmao.common.bean.BaseResult;
import com.lanmao.core.share.dto.DepMemberDTO;
import com.lanmao.core.share.service.DepMemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
public class DepMemberServiceImpl extends BaseService<DepMemberDTO> implements DepMemberService {


    @Override
    public BaseResult<String> login(@RequestBody DepMemberDTO depMemberDTO) {
        return null;
    }

    @Override
    public BaseResult<Long> save(@RequestBody DepMemberDTO saveObject) {
        return null;
    }

    @Override
    public BaseResult<List<DepMemberDTO>> queryList(@RequestBody DepMemberDTO queryObj) {
        return null;
    }

    @Override
    public BaseResult<DepMemberDTO> queryOne(@RequestBody DepMemberDTO queryObj) {
        return null;
    }

    @Override
    public BaseResult<Integer> updateById(@RequestBody DepMemberDTO updateObj) {
        return null;
    }

    @Override
    public BaseResult<Integer> deleteById(@RequestBody DepMemberDTO deleteObj) {
        return null;
    }
}
