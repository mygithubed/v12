package com.it.v12.common.base;

import java.util.List;

/**
 *
 * @param <T>
 */
public interface IBaseDao<T> {

    /**
     *
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Long id);

    int insert(T record);

    int insertSelective(T record);

    T selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(T record);

    int updateByPrimaryKeyWithBLOBs(T record);

    int updateByPrimaryKey(T record);

    public List<T> list();
}
