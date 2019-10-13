package com.lanmao.common.base;

import java.util.List;

public abstract class BaseRepository<T> {


    /**
     * 保存核心对象
     * @param saveObject
     * @return
     */
    public abstract Long save(T saveObject);


    /**
     * 根据主键查询
     * @param id
     * @return
     */
    public abstract T queryById(Long id);


    /**
     *
     * 查询List
     * @param query
     * @return
     */
    public abstract List<T> queryList(T query);


    /**
     *
     * 查询对象
     * @param query
     * @return
     */
    public abstract T queryOne(T query);


    /**
     *
     * 更新对象
     * @param updateObject
     * @return
     */
    public abstract int updateById(T updateObject);
}
