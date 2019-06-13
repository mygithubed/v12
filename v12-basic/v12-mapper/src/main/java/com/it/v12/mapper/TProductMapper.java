package com.it.v12.mapper;


import com.it.v12.entity.TProduct;
import com.it.v12.common.base.IBaseDao;

import java.util.List;

public interface TProductMapper extends IBaseDao<TProduct>{

    Long batchUpdateByFlag(List<Long> listId);
}
