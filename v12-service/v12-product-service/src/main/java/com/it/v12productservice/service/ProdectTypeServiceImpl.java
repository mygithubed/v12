package com.it.v12productservice.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.it.v12.api.IProdectTypeService;
import com.it.v12.common.base.BaseServiceImpl;
import com.it.v12.common.base.IBaseDao;
import com.it.v12.entity.TProductType;
import com.it.v12.mapper.TProductTypeMapper;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Author:曾志鹏
 * Date:2019/6/15
 * Time:10:48
 */
@Service
public class ProdectTypeServiceImpl extends BaseServiceImpl<TProductType> implements IProdectTypeService {

    @Autowired
    private TProductTypeMapper productTypeMapper;

    @Override
    public IBaseDao<TProductType> getBaseDao() {
        return productTypeMapper;
    }
}
