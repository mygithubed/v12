package com.it.v12.api;

import com.it.v12.common.pojo.RsetBean;

import java.util.ResourceBundle;

/**
 * 全量同步数据接口 （初始化）
 * @author:曾志鹏
 * Date:2019/6/17
 * Time:11:12
 */
public interface ISearchApi {

    /**
     * 返回的RseBean是在 common中的pojo中定义的
     * @return
     */
     RsetBean syncAllData();

    /**
     * 搜索服务的接口
     * @param searchWords
     * @return
     */
     RsetBean searchByKeyWord(String searchWords);

    /**
     * 根据ID进行增量同步
     * @param id
     * @return
     */
     RsetBean queryDataById(Long id);
}
