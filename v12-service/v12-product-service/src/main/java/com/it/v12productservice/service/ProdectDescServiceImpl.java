package com.it.v12productservice.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.it.v12.api.IProdectDescService;
import com.it.v12.common.base.BaseServiceImpl;
import com.it.v12.common.base.IBaseDao;
import com.it.v12.entity.TProductDesc;
import com.it.v12.mapper.TProductDescMapper;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author:曾志鹏
 * Date:2019/6/19
 * Time:11:31
 */
@Service
public class ProdectDescServiceImpl extends BaseServiceImpl<TProductDesc> implements IProdectDescService {



    @Autowired
    private TProductDescMapper productDescMapper;

    /**
     * 根据商品的ID返回商品的描述
     *
     * @param id
     * @return
     */
    @Override
    public String selectByproductDesc(Long id) {
        return productDescMapper.selectProductDescByProductId(id);
    }

    @Override
    public IBaseDao<TProductDesc> getBaseDao() {
        return productDescMapper;
    }
}
