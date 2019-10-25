package com.lanmao.core.service;

import com.lanmao.common.bean.BaseResult;
import com.lanmao.core.repository.UserConsumeRepository;
import com.lanmao.core.share.dto.UserConsumeDTO;
import com.lanmao.core.share.service.UserConsumeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class UserConsumeServiceImpl implements UserConsumeService {

    @Resource
    private UserConsumeRepository userConsumeRepository;

    @Override
    public BaseResult<Long> save(UserConsumeDTO saveObject) {
        BaseResult<Long> baseResult = new BaseResult<>();
        baseResult.setCodeSuccess();
        Long newId = userConsumeRepository.save(saveObject);
        baseResult.setData(newId);
        return baseResult;
    }
}
