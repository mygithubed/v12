package com.it.v12centerweb.pojo;

/**
 * Date:2019/6/14
 * Time:19:20
 * @author 曾志鹏
 */
public class Wangeditor3UploadRestBean {

    /**
     * 这些属性需要根据官方文档来写
     */
    private  String errno;

    private String[] data;

    public Wangeditor3UploadRestBean(){}

    public Wangeditor3UploadRestBean(String errno, String[] data) {
        this.errno = errno;
        this.data = data;
    }

    public String getErrno() {
        return errno;
    }

    public void setErrno(String errno) {
        this.errno = errno;
    }

    public String[] getData() {
        return data;
    }

    public void setData(String[] data) {
        this.data = data;
    }
}
