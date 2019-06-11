package com.it.v12productservice.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.it.v12.api.IProdectService;
import com.it.v12.common.base.BaseServiceImpl;
import com.it.v12.common.base.IBaseDao;
import com.it.v12.entity.TProduct;
import com.it.v12.mapper.TProductMapper;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Author:曾志鹏
 * Date:2019/6/11
 * Time:17:15
 */
@Service
public class ProductServiceImpl extends BaseServiceImpl<TProduct> implements IProdectService {

    //注入具体的
    @Autowired
    private TProductMapper productMapper;

    @Override
    public IBaseDao<TProduct> getBaseDao() {
        return productMapper;
    }
}
