package com.it.v12centerweb.pojo;

/**
 * @author:曾志鹏
 * Date:2019/6/15
 * Time:16:54
 */
public class ProductTypeResut {

    private Long id;
    private String types;

    public ProductTypeResut(Long id, String types) {
        this.id = id;
        this.types = types;
    }

    public ProductTypeResut() {

    }

    public String getTypes() {
        return types;
    }

    public void setTypes(String types) {
        this.types = types;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
