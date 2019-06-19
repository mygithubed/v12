package com.it.v12.entity;

import java.io.Serializable;
/**
 * 商品详情实体类
 * @author Administrator
 */
public class TProductDesc implements Serializable{
    private Long id;

    private Long productId;

    private String productDesc;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc == null ? null : productDesc.trim();
    }
}
