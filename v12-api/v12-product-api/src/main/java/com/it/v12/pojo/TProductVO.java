package com.it.v12.pojo;

import com.it.v12.entity.TProduct;

import java.io.Serializable;

/**
 * Author:曾志鹏
 * Date:2019/6/12
 * Time:19:44
 */

public class TProductVO implements Serializable {

    private TProduct product;

    private String productDesc;


    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public TProduct getProduct() {
        return product;
    }

    public void setProduct(TProduct product) {
        this.product = product;
    }
}
