package com.it.v12.mapper;


import com.it.v12.entity.TProduct;
import com.it.v12.common.base.IBaseDao;

import java.util.List;

/**
 *商品对应的Mapping接口
 * @author Administrator
 */
public interface TProductMapper extends IBaseDao<TProduct>{

    /**
     *修改标记
     * @param listId
     * @return
     */
    Long batchUpdateByFlag(List<Long> listId);
}
