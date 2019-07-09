package com.it.v12msweb.controller;

import com.it.v12msweb.exception.SeckillException;
import com.it.v12msweb.pojo.RestBean;
import com.it.v12msweb.service.ISeckillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author :曾志鹏
 * Date:2019/7/9
 * Time:11:40
 */
@Controller
@RequestMapping("seckill")
public class SeckillController {

    @Autowired
    private ISeckillService seckillService;


    @RequestMapping("sale")
    @ResponseBody
    public RestBean sales(Long productId ,Long userId){

        try {
            seckillService.sale(productId,userId);
            return new RestBean("200","抢购成功！！");
        }catch (SeckillException e){
            return new RestBean("404",e.getMessage());
        }
    }
}
