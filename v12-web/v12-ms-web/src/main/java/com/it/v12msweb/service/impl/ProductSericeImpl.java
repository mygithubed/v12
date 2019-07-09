package com.it.v12msweb.service.impl;

import com.it.v12msweb.entity.TProduct;
import com.it.v12msweb.mapper.TProductMapper;
import com.it.v12msweb.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author :曾志鹏
 * Date:2019/7/8
 * Time:17:32
 */
@Service
public class ProductSericeImpl  implements IProductService{

    @Autowired
    private TProductMapper productMapper;

    @Override
    @Cacheable(value = "product",key = "#id")
    public TProduct getProductById(Long id) {
        return productMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional
    public boolean sale(Long id) {
        /**查看库存的信息**/
       int  stole =  productMapper.getStoreById(id);
        /**更新库存**/
        if(stole>0){
            productMapper.updateStoreById(id);
            return true;
        }
        return false;
    }
}
