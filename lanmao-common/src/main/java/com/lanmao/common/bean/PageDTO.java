package com.lanmao.common.bean;

import java.util.List;

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

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public T getParams() {
        return params;
    }

    public void setParams(T params) {
        this.params = params;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }
}
