package com.it.v12searchweb.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.it.v12.api.ISearchApi;
import com.it.v12.common.pojo.RsetBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Author:曾志鹏
 * Date:2019/6/17
 * Time:16:57
 */
@Controller()
@RequestMapping("search")
public class SearchController {

    @Reference
    private ISearchApi searchApi;
    
    @RequestMapping("selectByWords")
    public String selectByWords(String keywords, Model model){

        //获取到数据
        RsetBean rsetBean = searchApi.searchByKeyWord(keywords);
        //将数据存到model中
        model.addAttribute("list",rsetBean);
        //页面的回显
        return "list";
    }
}
