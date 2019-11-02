package com.lanmao.user.controller;

import com.lanmao.common.annotation.IgnorePath;
import com.lanmao.common.bean.BaseResult;
import com.lanmao.common.utils.DateUtils;
import com.lanmao.core.share.dto.OrderDTO;
import com.lanmao.core.share.dto.TimeBlockDTO;
import com.lanmao.core.share.dto.TimeUnitDTO;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@Slf4j
@RequestMapping(value = "/v1/appoint")
public class AppointController {


    /**
     *
     * 查询时间
     * @param bookDTO
     * @return
     */
    @RequestMapping(value = "/selectTime", method = RequestMethod.POST)
    @IgnorePath
    public BaseResult<List<TimeBlockDTO>> selectTime(@RequestBody OrderDTO bookDTO) {
        BaseResult<List<TimeBlockDTO>> baseResult = new BaseResult<>();
        baseResult.setCodeSuccess();

        List<TimeBlockDTO> list = new ArrayList<>();
        list.add(getTimeBlock("上午", "07:00", "12:00"));
        list.add(getTimeBlock("下午", "13:00", "18:00"));
        list.add(getTimeBlock("晚上", "19:00", "23:00"));
        baseResult.setData(list);
        return baseResult;
    }

    private TimeBlockDTO getTimeBlock(String title, String startTime, String endTime) {
        TimeBlockDTO timeBlockDTO = null;
        timeBlockDTO = new TimeBlockDTO();
        timeBlockDTO.setTitle(title);
        timeBlockDTO.setText("（"+startTime + "-"+ endTime +"）");
        List<TimeUnitDTO> timeUnitDTOS = new ArrayList<>();
        Date startDate = DateUtils.parse("2019-11-01 " + startTime + ":00", DateUtils.YYYY_MM_DD_HH_MM_SS);
        Date endDate = DateUtils.parse("2019-11-01 " + endTime + ":00", DateUtils.YYYY_MM_DD_HH_MM_SS);
        DateTime start = new DateTime(startDate);
        while (start.toDate().getTime() <= endDate.getTime()) {
            Date time = start.toDate();
            String text = DateUtils.format(time, DateUtils.HH_MM);
            TimeUnitDTO unitDTO = new TimeUnitDTO();
            unitDTO.setText(text);
            timeUnitDTOS.add(unitDTO);
            start = start.plusMinutes(30);
        }
        timeBlockDTO.setTimeUnitList(timeUnitDTOS);
        return timeBlockDTO;
    }
}
