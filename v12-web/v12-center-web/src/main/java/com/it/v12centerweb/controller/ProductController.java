package com.it.v12centerweb.controller;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.rpc.filter.tps.TPSLimiter;
import com.github.pagehelper.PageInfo;
import com.it.v12.api.IProdectService;
import com.it.v12.entity.TProduct;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
/**
 * Author:曾志鹏
 * Date:2019/6/11
 * Time:19:21
 */
@Controller
@RequestMapping("product")
public class ProductController {

    @Reference
    private IProdectService prodectService;

    /**
     *
     * @param id
     * @return
     */
    @RequestMapping("get/{id}")
    @ResponseBody
    public TProduct getByid(@PathVariable("id") Long id){
        return prodectService.selectByPrimaryKey(id);
    }

    /**
     *
     * @param model
     * @return
     */
    @RequestMapping("list")
    public String list(Model model){
        //获取后台的数据
        List<TProduct> list = prodectService.list();
          //传入页面显示
        model.addAttribute("list",list);
        //页面的跳转
        return "product/list";
    }

    @RequestMapping("page/{index}/{size}")
    public String pageList(@PathVariable("index") Integer index,
                       @PathVariable("size") Integer size,
                       Model model){

        PageInfo<TProduct> pages = prodectService.page(index,size);

        model.addAttribute("page",pages);

        return "product/list";
    }
}
