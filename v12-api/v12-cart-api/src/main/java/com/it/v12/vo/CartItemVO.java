package com.it.v12.vo;

import com.it.v12.entity.TProduct;

import java.io.Serializable;
import java.util.Date;

/**
 * @author :曾志鹏
 * Date:2019/7/1
 * Time:11:55
 */
public class CartItemVO implements Serializable,Comparable<CartItemVO>{

    private TProduct product;

    private  Integer count;

    private Date updateTime;


    public CartItemVO(TProduct product, Integer count, Date updateTime) {
        this.product = product;
        this.count = count;
        this.updateTime = updateTime;
    }

    public CartItemVO() {

    }

    public TProduct getProduct() {
        return product;
    }

    public void setProduct(TProduct product) {
        this.product = product;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "CartItemVO{" +
                "product=" + product +
                ", count=" + count +
                ", updateTime=" + updateTime +
                '}';
    }

    public int compareTo(CartItemVO o) {
        int i = (int)(o.getUpdateTime().getTime() - this.getUpdateTime().getTime());
        return i;
    }
}
