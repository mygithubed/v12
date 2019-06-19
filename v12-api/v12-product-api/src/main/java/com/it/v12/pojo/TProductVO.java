package com.it.v12.pojo;

import com.it.v12.entity.TProduct;

import java.io.Serializable;

/**
 * 商品和商品详情的关联
 * @author:曾志鹏
 * Date:2019/6/12
 * Time:19:44
 */

public class TProductVO implements Serializable {

    private TProduct product;

    private String productDesc;

    /**
     *
     * @return
     */
    public String getProductDesc() {
        return productDesc;
    }

    /**
     *
     * @param productDesc
     */
    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    /**
     *
     * @return
     */
    public TProduct getProduct() {
        return product;
    }

    /**
     *
     * @param product
     */
    public void setProduct(TProduct product) {
        this.product = product;
    }
}
