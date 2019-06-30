package com.it.v12.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * @author :曾志鹏
 * Date:2019/6/29
 * Time:11:25
 */
public class CartItem implements Serializable,Comparable<CartItem>{

    private static final long serialVersionUID = 1L;

    public CartItem() {

    }

    private  Long product_id;
    private int count;
    private Date updateDate;

    public CartItem(Long product_id, int count, Date updateDate) {
        this.product_id = product_id;
        this.count = count;
        this.updateDate = updateDate;
    }

    public Long getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Long product_id) {
        this.product_id = product_id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "product_id=" + product_id +
                ", count=" + count +
                ", updateDate=" + updateDate +
                '}';
    }

    public int compareTo(CartItem o) {

        int time=(int)(o.getUpdateDate().getTime()-this.getUpdateDate().getTime());

        return time;
    }
}
