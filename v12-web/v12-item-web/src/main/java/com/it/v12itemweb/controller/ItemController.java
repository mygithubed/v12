package com.it.v12itemweb.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.it.v12.api.IProdectService;
import com.it.v12.common.pojo.RsetBean;
import com.it.v12.entity.TProduct;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * 商品详情
 * Date:2019/6/18
 * Time:11:30
 * @author： 曾志鹏
 */
@Controller
@RequestMapping("item")
public class ItemController {

    @Autowired
    private Configuration  configuration;

    @Reference
    private IProdectService productService;

    @RequestMapping("createHtml/{id}")
    @ResponseBody
    public RsetBean createHtml(@PathVariable("id") Long id){

        //根据ID来获取商品的信息
        TProduct product = productService.selectByPrimaryKey(id);
        try {
            //获取模板对象
            Template template = configuration.getTemplate("item.ftl");
            //设置模板数据
            Map<String,Object>  map = new HashMap<>();
            map.put("product",product);
            //4.生成静态页
            //获取static的路径
            String serverpath= ResourceUtils.getURL("classpath:static").getPath();
            //构建输出流对象，根据ID来命名。
            FileWriter out = new FileWriter(serverpath+ File.separator+id+".html");
            //生成静态页
            template.process(map,out);
        } catch (IOException e) {
            e.printStackTrace();
            return new RsetBean("404","获取数据错误！");
        }catch (TemplateException e) {
            e.printStackTrace();
            return new RsetBean("404","静态页面创建有误！");
        }
        return new RsetBean("200","静态页面生成成功！！");
    }

}
