package com.it.v12.mapper;

import com.it.v12.common.base.IBaseDao;
import com.it.v12.entity.TProductDesc;

/**
 * 商品的实体对应的Mapping
 * @author Administrator
 */
public interface TProductDescMapper extends IBaseDao<TProductDesc>{
    /**
     * 根据ID获取商品的详情描述
     * @param productId
     * @return
     */
    String selectProductDescByProductId(Long productId);

    /**
     * 根据商品的ID来更改商品的描述
     * @param desc
     */
    void updateByByProductId(TProductDesc desc);
}
