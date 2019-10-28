package com.lanmao.core.repository;

import com.lanmao.common.base.BaseRepository;
import com.lanmao.common.constants.YesOrNoEnum;
import com.lanmao.common.utils.CommonUtils;
import com.lanmao.core.dataobject.SmsDO;
import com.lanmao.core.mapper.SmsDAO;
import com.lanmao.core.share.dto.SmsDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Repository
@Slf4j
public class SmsRepository extends BaseRepository<SmsDTO> {

    @Resource
    private SmsDAO smsDAO;

    @Override
    public Long save(SmsDTO saveObject) {
        SmsDO record = new SmsDO();
        CommonUtils.copyProperties(saveObject, record);
        CommonUtils.setInsertDefaultValue(record);
        smsDAO.insert(record);
        return record.getId();
    }

    @Override
    public SmsDTO queryById(Long id) {
        return null;
    }

    @Override
    public List<SmsDTO> queryList(SmsDTO query) {
        query.setIsDeleted(YesOrNoEnum.NO.getCode());
        Map<String, Object> objMap = CommonUtils.toQueryMap(query);
        List<SmsDO> list = smsDAO.selectByMap(objMap);
        return CommonUtils.convertList(list, SmsDTO.class);
    }

    @Override
    public int countQueryList(SmsDTO query) {
        return 0;
    }

    @Override
    public SmsDTO queryOne(SmsDTO query) {
        List<SmsDTO> list = queryList(query);
        if (CollectionUtils.isNotEmpty(list)) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public int updateById(SmsDTO updateObject) {
        return 0;
    }

    @Override
    public int deleteById(SmsDTO deleteObject) {
        return 0;
    }

    public boolean checkSms(String mobile, String smsCode) {
        SmsDO record = smsDAO.selectLastOne(mobile);
        if (record == null) return false;
        return Objects.equals(smsCode, record.getCode());
    }
}
