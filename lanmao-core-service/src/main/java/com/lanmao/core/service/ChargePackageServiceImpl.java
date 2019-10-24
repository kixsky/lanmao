package com.lanmao.core.service;

import com.lanmao.common.bean.BaseResult;
import com.lanmao.core.repository.ChargePackageRepository;
import com.lanmao.core.share.dto.ChargePackageDTO;
import com.lanmao.core.share.service.ChargePackageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Slf4j
public class ChargePackageServiceImpl implements ChargePackageService {

    @Resource
    private ChargePackageRepository chargePackageRepository;

    @Override
    public BaseResult<List<ChargePackageDTO>> queryList(@RequestBody ChargePackageDTO queryObject) {
        BaseResult<List<ChargePackageDTO>> baseResult = new BaseResult<>();
        baseResult.setCodeSuccess();
        List<ChargePackageDTO> list = chargePackageRepository.queryList(queryObject);
        baseResult.setData(list);
        return baseResult;
    }
}
