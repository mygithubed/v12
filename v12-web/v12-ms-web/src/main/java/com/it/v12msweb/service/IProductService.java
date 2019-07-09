package com.it.v12msweb.service;

import com.it.v12msweb.entity.TProduct;

/**
 * @author :曾志鹏
 * Date:2019/7/8
 * Time:17:31
 */
public interface IProductService {

    /**
     *根据id获取商品
     * @param id 商品的ID
     * @return 返回对应的商品
     */
    TProduct getProductById(Long id);

    /**
     * 秒杀
     * @param id ID
     * @return 返回
     * */
    boolean sale(Long id);
}

