package com.lanmao.core.share.dto;

import lombok.Data;

import java.util.List;

@Data
public class TimeBlockDTO {

    private String title;
    private String text;

    private List<TimeUnitDTO> timeUnitList;
}
