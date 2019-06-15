package com.it.v12indexweb.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.it.v12.api.IProdectTypeService;
import com.it.v12.entity.TProductType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.OutputStream;
import java.util.List;

/**
 * Author:曾志鹏
 * Date:2019/6/15
 * Time:11:45
 */
@Controller
@RequestMapping("index")
public class IndexController {

    @Reference
    private IProdectTypeService prodectTypeService;

    @RequestMapping("home")
    public String index(Model model){
        //调用服务获取到商品的类别 信息
        List<TProductType> listTProductType = prodectTypeService.list();

        model.addAttribute("list",listTProductType);

        return "index";
    }
}
