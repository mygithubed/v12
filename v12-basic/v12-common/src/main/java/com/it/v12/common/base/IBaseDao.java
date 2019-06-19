package com.it.v12.common.base;

import java.util.List;

/**
 *
 * @author:曾志鹏
 * @param <T>
 */
public interface IBaseDao<T> {

    /**
     * 根据ID删除
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Long id);

    /**
     *插入数据的接口
     * @param record
     * @return
     */
    int insert(T record);

    /**
     *修改
     * @param record
     * @return
     */
    int insertSelective(T record);

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    T selectByPrimaryKey(Long id);

    /**
     * 根据ID修改信息,判断字段值是否传递了
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(T record);

    /**
     *  根据ID修改信息
     * @param record
     * @return
     */
    int updateByPrimaryKeyWithBLOBs(T record);

    /**
     * 根据ID修改信息
     * @param record
     * @return
     */
    int updateByPrimaryKey(T record);

    /**
     * 查询所有的数据
     * @return
     */
    List<T> list();
}
