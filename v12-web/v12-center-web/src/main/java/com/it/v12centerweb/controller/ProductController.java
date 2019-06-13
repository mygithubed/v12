package com.it.v12centerweb.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import com.it.v12.api.IProdectService;
import com.it.v12.common.pojo.RsetBean;
import com.it.v12.entity.TProduct;
import com.it.v12.pojo.TProductVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
     *根据ID查询数据
     * @param id
     * @return
     */
    @RequestMapping("get/{id}")
    @ResponseBody
    public TProduct getByid(@PathVariable("id") Long id){
        return prodectService.selectByPrimaryKey(id);
    }

    /**
     *显示全部数据
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
        return "product/ss";
    }

    /**
     * 分页的请求
     * @param index
     * @param size
     * @param model
     * @return
     */
    @RequestMapping("page/{index}/{size}")
    public String pageList(@PathVariable("index") Integer index,
                       @PathVariable("size") Integer size,
                       Model model){

        PageInfo<TProduct> pages = prodectService.page(index,size);

        model.addAttribute("page",pages);

        return "product/list";
    }

    /**
     * 添加功能的实现
     * @param vo
     * @return
     */
    @PostMapping("add")
    public String add(TProductVO vo){

        Long ids  = prodectService.saves(vo);

        return "redirect:/product/page/1/1";
    }

    /**
     * 批量删除的实现
     * @param ids
     * @return
     */
    @PostMapping("delteSelects")
    @ResponseBody
    public RsetBean bathchDel(@RequestParam List<Long> ids){
        Long conts = prodectService.batchDel(ids);
        if (conts > 0){
            return new RsetBean("200","批量删除成功了！！");
        }
        return new RsetBean("404","批量删除失败！！！！");
    }

    /**
     * 统一的一个类管理 传入前端的数据格式
     * @param id
     * @return
     */
    @PostMapping("deleteById/{id}")
    @ResponseBody
    public RsetBean delete(@PathVariable("id") Long id){
       int num =  prodectService.deleteByPrimaryKey(id);
       if (num > 0){
           return new RsetBean("200","删除成功了！！");
       }
        return new RsetBean("404","删除失败！！！！");
    }

    /**
     * 查询需要修改的数据
     * @param id
     * @return
     */
    @PostMapping("toUpdate/{id}")
    @ResponseBody
    public RsetBean toUpdate(@PathVariable("id") Long id){
        System.out.println(id+"jjjjjjjjjjjjjjjjjjjjjjjjjj");
        TProduct product = prodectService.selectByPrimaryKey(id);
        System.out.println(product);
        return new RsetBean("200","修改！！");
    }
}
