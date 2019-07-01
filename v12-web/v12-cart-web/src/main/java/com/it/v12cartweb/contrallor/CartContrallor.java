package com.it.v12cartweb.contrallor;

import com.alibaba.dubbo.config.annotation.Reference;
import com.it.v12.api.ICartService;
import com.it.v12.common.pojo.RsetBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * @author : 曾志鹏
 * Date:2019/6/30
 * Time:23:43
 */
@Controller
@RequestMapping("cart")
public class CartContrallor {

    @Reference
    private ICartService cartService;

    /**
     * 添加购物车
     * @param productId 商品id
     * @param count 数量
     * @param uuid
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping("add/{productId}/{count}")
    public RsetBean add(@PathVariable Long productId,
                        @PathVariable Integer count,
                        @CookieValue(name = "user_cart",required = false) String uuid,
                        HttpServletResponse response){
        //判断购物车是否存在
        if(uuid==null || "".equals(uuid)){
            uuid = UUID.randomUUID().toString();
        }
        //添加到购物车
        RsetBean rsetBean = cartService.add(uuid, productId, count);
        String sucess = "200";
        if(sucess.equals(rsetBean.getStatCodes())){
            //cookie存取来
            cookReflush(uuid, response);
        }
        return rsetBean;
    }

    /**
     *  查询购物车的商品
     * @param  uuid
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping("query")
    public RsetBean queryCart(@CookieValue(name = "user_cart",required = false) String uuid,
                              HttpServletResponse response){
        //判断购物车是否存在
        if(uuid==null || "".equals(uuid)){
            return new RsetBean("404","购物车不存在！");
        }
        RsetBean query = cartService.query(uuid);
        String sucess = "200";
        if(sucess.equals(query.getStatCodes())){
            //刷新cooker中的有效期
            cookReflush(uuid, response);
        }


        return query;
    }

    /**
     * 刷新cooker的值
     * @param uuid
     * @param response
     */
    private void cookReflush(@CookieValue(name = "user_cart", required = false) String uuid, HttpServletResponse response) {
        //cookie存取来
        Cookie cookie = new Cookie("user_cart",uuid);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(7*24*60*60);
        response.addCookie(cookie);
    }


    /**
     * 删除购物车
     * @param uuid
     * @param response
     * @return
     */
    @RequestMapping("del/{productId}")
    @ResponseBody
    public RsetBean del(@CookieValue(name = "user_cart",required = false) String uuid,
                      @PathVariable("productId") Long productId,
                        HttpServletResponse response){

    if(uuid == null || "".equals(uuid)){
        return new RsetBean("404","购物车为空！");
    }
        RsetBean remove = cartService.remove(uuid,productId);
        String sucess = "200";
        if(sucess.equals(remove.getStatCodes())){
            //刷新cooker中的有效期
            cookReflush(uuid, response);
        }
        return remove;
    }

    /**
     * 更新购物车信息
     * @param uuid 购物车
     * @param productId 商品的ID
     * @param count 商品的和数量
     * @param response 响应
     * @return
     */
    @RequestMapping("update/{productId}/{count}")
    @ResponseBody
    public RsetBean update(@CookieValue(name = "user_cart",required = false) String uuid,
                           @PathVariable("productId") Long productId,
                           @PathVariable("count") Integer count,
                           HttpServletResponse response){
        if(uuid == null || "".equals(uuid)){
            return new RsetBean("404","购物车为空！");
        }
        RsetBean update = cartService.update(uuid,productId,count);
        String sucess = "200";
        if(sucess.equals(update.getStatCodes())){
            //刷新cooker中的有效期
            cookReflush(uuid, response);
        }
        return update;
    }
}
