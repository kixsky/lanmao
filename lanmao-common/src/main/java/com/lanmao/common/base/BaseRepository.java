package com.lanmao.common.base;

import java.util.List;

public interface BaseRepository<T> {


    Long save(T saveObject);


    T queryById(Long id);


    List<T> queryList(T query);


    T queryOne(T query);


    int updateById(T updateObject);
}
