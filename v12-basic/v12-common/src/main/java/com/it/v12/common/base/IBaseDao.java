package com.it.v12.common.base;

/**
 * Author:曾志鹏
 * Date:2019/6/11
 * Time:12:04
 */
public interface IBaseDao<T> {

    int deleteByPrimaryKey(Long id);

    int insert(T record);

    int insertSelective(T record);

    T selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(T record);

    int updateByPrimaryKeyWithBLOBs(T record);

    int updateByPrimaryKey(T record);
}
