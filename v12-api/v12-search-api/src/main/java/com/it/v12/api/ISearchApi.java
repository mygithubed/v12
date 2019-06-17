package com.it.v12.api;

import com.it.v12.common.pojo.RsetBean;

import java.util.ResourceBundle;

/**
 * Author:曾志鹏
 * Date:2019/6/17
 * Time:11:12
 * 全量同步数据接口 （初始化）
 */
public interface ISearchApi {

    /**
     * 返回的RseBean是在 common中的pojo中定义的
     * @return
     */
    public RsetBean syncAllData();

    /**
     * 搜索服务的接口
     * @param searchWords
     * @return
     */
    public RsetBean searchByKeyWord(String searchWords);
}
