package com.it.v12msweb.mapper;


import com.it.v12msweb.entity.TProduct;

/**
 * @author Administrator
 */
public interface TProductMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TProduct record);

    int insertSelective(TProduct record);

    TProduct selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TProduct record);

    int updateByPrimaryKey(TProduct record);

    int getStoreById(Long id);

    void updateStoreById(Long id);
}
