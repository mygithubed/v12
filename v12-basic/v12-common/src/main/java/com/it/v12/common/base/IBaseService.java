package com.it.v12.common.base;

import java.util.List;

/**
 * 公共的接口
 * @author 曾志鹏
 * Date:2019/6/11
 * Time:12:06
 */
public interface IBaseService<T> {
    /**
     * 根据ID删除
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 插入数据
     * @param record
     * @return
     */
    int insert(T record);

    /**
     *插入数据
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
     * 修改
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(T record);

    /**
     *修改指定的数据
     * @param record
     * @return
     */
    int updateByPrimaryKeyWithBLOBs(T record);

    /**
     * 更新
     * @param record
     * @return
     */
    int updateByPrimaryKey(T record);

    /**
     * 查询所有
     * @return
     */
    List<T> list();
}
