package com.lanmao.common.base;

import java.util.List;

public interface BaseRepository<T> {


    /**
     * 保存核心对象
     * @param saveObject
     * @return
     */
    Long save(T saveObject);


    /**
     * 根据主键查询
     * @param id
     * @return
     */
    T queryById(Long id);


    /**
     *
     * 查询List
     * @param query
     * @return
     */
    List<T> queryList(T query);


    /**
     *
     * 查询对象
     * @param query
     * @return
     */
    T queryOne(T query);


    /**
     *
     * 更新对象
     * @param updateObject
     * @return
     */
    int updateById(T updateObject);
}
