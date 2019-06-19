package com.it.v12.common.pojo;

import java.io.Serializable;

/**
 * Author:曾志鹏
 * Date:2019/6/12
 * Time:23:40
 * @author Administrator
 */
public class RsetBean<T> implements Serializable{

    private  String statCodes;

    private T data;

    public RsetBean() {

    }

    public RsetBean(String statCodes, T data) {

        this.statCodes = statCodes;
        this.data = data;
    }

    public String getStatCodes() {
        return statCodes;
    }

    public void setStatCodes(String statCodes) {
        this.statCodes = statCodes;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
