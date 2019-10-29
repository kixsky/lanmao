package com.lanmao.common.bean;

import lombok.Data;

import java.util.List;

@Data
public class PageDTO<T> {

    public static final int DEFAULT_PAGE_SIZE = 10;

    private Integer page;

    private Integer pageSize;

    private T params;

    private List<T> list;

    private Integer totalCount;

    public void setDefaultValue() {
        if (this.page == null) {
            this.page = 1;
        }
        if (this.pageSize == null) {
            this.pageSize = DEFAULT_PAGE_SIZE;
        }
    }
}
