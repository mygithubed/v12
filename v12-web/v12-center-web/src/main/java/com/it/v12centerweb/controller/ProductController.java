package com.it.v12centerweb.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.it.v12.api.IProdectDescService;
import com.it.v12.api.IProdectService;
import com.it.v12.api.IProdectTypeService;
import com.it.v12.api.ISearchApi;
import com.it.v12.common.pojo.RsetBean;
import com.it.v12.common.utils.HttpClientUtils;
import com.it.v12.entity.TProduct;
import com.it.v12.entity.TProductType;
import com.it.v12.pojo.TProductVO;
import com.it.v12centerweb.pojo.ProductTypeResut;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
/**
 * 商品的相关
 * @author:曾志鹏
 * Date:2019/6/11
 * Time:19:21
 */
@Controller
@RequestMapping("product")
public class ProductController {

    @Reference
    private IProdectService prodectService;

    @Reference
    private IProdectTypeService prodectTypeService;

    @Reference
    private IProdectDescService prodectDescService;

    @Reference
    private ISearchApi searchApi;

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

        //通知索引系统添加索引系统进行同步
        /**searchApi.syncAllData();**/
        searchApi.queryDataById(ids);
        //生成商品对应的页面
        HttpClientUtils.doGet("http://localhost:9093/item/createHtml/"+ids);

        return "redirect:/product/page/1/5";
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
     * 根据ID来删除商品的信息
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
        //根据商品的ID来查询对应商品的信息
        TProduct product = prodectService.selectByPrimaryKey(id);
        System.out.println("商品的信息");
        System.out.println(product);
        //根据商品的ID来查询对应商品的描述信息
        String productDesc =prodectDescService.selectByproductDesc(id);
        System.out.println("商品的描述信息：");
        System.out.println(productDesc);

        TProductVO tProductVO = new TProductVO();
        tProductVO.setProduct(product);
        tProductVO.setProductDesc(productDesc);

        return new RsetBean("200",tProductVO);
    }


    /**
     * 查询商品的类别数据
     * @return
     */
    @RequestMapping("selectProductType")
    @ResponseBody
    public String selectProductType(){
        //查询出所有的数据
        List<TProductType> list = prodectTypeService.list();
        //对数据进行了，包装，只需要商品ID和商品的类别两个字段
        List<ProductTypeResut>  typeResuts = new ArrayList<>();
        for (TProductType tProductType : list) {
            Long id = tProductType.getId();
            String name = tProductType.getName();
            typeResuts.add(new ProductTypeResut(id,name));
        }
        //创建一个json的数组
        JSONArray jsonArray = new JSONArray();
        //创建了一个json的对象
        JSONObject jsonObject = null;
        //创建返回的类型对象
        ProductTypeResut productTypeResut = null;
        for (ProductTypeResut tesut : typeResuts) {
            jsonObject = new JSONObject();
            jsonObject.put("id",tesut.getId());
            jsonObject.put("types",tesut.getTypes());
            jsonArray.add(jsonObject);
        }
        return jsonArray.toString();
    }

    @RequestMapping("edit")
    public  String edit(TProductVO vo){
        TProduct product = vo.getProduct();
        String productDesc = vo.getProductDesc();

        System.out.println("商品："+product);
        System.out.println("商品的描述：");
        System.out.println(productDesc);

        prodectService.updateById(vo);

        return "redirect:/product/page/1/5";
    }
}
