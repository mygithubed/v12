package com.it.v12msweb.controller;

import com.it.v12msweb.entity.TProduct;
import com.it.v12msweb.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author : 曾志鹏
 * Date:2019/7/8
 * Time:17:30
 */
@Controller
@RequestMapping("/product")
public class ProductController {


    @Autowired
    private IProductService productService;

    @RequestMapping("getbyId")
    public String getById(Long id , Model model){
        TProduct productById = productService.getProductById(id);
        model.addAttribute("product",productById);
        return "item";
    }



    @RequestMapping("sale")
    @ResponseBody
    public String sale(Long id){
       boolean flag =  productService.sale(id);
        if(flag){
            return "SUCCESS";
        }else {
            return "faild";
        }
    }
}
