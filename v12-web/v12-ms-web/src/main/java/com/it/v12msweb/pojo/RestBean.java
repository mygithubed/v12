package com.it.v12msweb.pojo;

import java.io.Serializable;

/**
 * @author :曾志鹏
 * Date:2019/7/9
 * Time:11:42
 */
public class RestBean<T> implements Serializable {

    private String startCode;
    private  T data;

    public RestBean(String startCode, T data) {
        this.startCode = startCode;
        this.data = data;
    }

    public RestBean() {

    }

    public String getStartCode() {
        return startCode;
    }

    public void setStartCode(String startCode) {
        this.startCode = startCode;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
