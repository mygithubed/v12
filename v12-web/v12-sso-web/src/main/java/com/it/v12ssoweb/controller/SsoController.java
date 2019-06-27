package com.it.v12ssoweb.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.it.v12.api.IUserService;
import com.it.v12.common.constant.CookieConstant;
import com.it.v12.common.pojo.RsetBean;
import com.it.v12.entity.TUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author : 曾志鹏
 * Date:2019/6/27
 * Time:11:46
 */
@Controller
@RequestMapping("sso")
public class SsoController {

    @Reference
    private IUserService userService;
    /**
     * 前往登入的页面
     * @return 返回login.html
     */
    @RequestMapping("tologin")
    public String toLogin(){
        return "login";
    }

    /**
     *  登入的功能
     * @param user  前端表单填写的数据
     * @param response 将cookie写客户端
     * @return 页面的跳转
     */
    @RequestMapping("login")
    public String login(TUser user, HttpServletResponse response){
        String code ="200";
        RsetBean checklogin =userService.checklogin(user);
        if(code.equals(checklogin.getStatCodes())){
            //将UUID存入cooker中
            Cookie cookie = new Cookie(CookieConstant.CK_TOKEN,checklogin.getData().toString());
            cookie.setPath("/");
            cookie.setHttpOnly(true);
            response.addCookie(cookie);
            //跳转到首页
            return "redirect:http://localhost:9091/index/home";
        }
        //登入失败，返回登入的页面
        return "login";
    }


    /**
     * 解析cooker
     * @param request 请求
     * @return 返回是否登入的状态
     */
    @RequestMapping("checkLoginStarts")
    @ResponseBody
    public RsetBean checkLoginStarts(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        if(cookies!=null){
            for (Cookie cookie : cookies) {
                if((CookieConstant.CK_TOKEN).equals(cookie.getName())){
                    String uuid = cookie.getValue();
                    return userService.checkLoginStarts(uuid);
                }
            }
        }
        return new RsetBean("404",null);
    }

    /**
     *  简化代码的方式 实现验证是否登入了
     * @param uuid 存入的凭证
     * @return 返回是否登入的消息
     */
    @RequestMapping("checkLoginStarts2")
    @ResponseBody
    public RsetBean checkLoginStarts2(@CookieValue(name = CookieConstant.CK_TOKEN,required = false)String uuid){
        if(uuid != null){
            return userService.checkLoginStarts(uuid);
        }
        return new RsetBean("404",null);
    }

    /**
     * 用户退出的方法
     * @param uuid 需要删除的凭证
     * @param response 相应到客户端的携带数据
     * @return 返回退出的状态
     */
    @RequestMapping("loginOut")
    @ResponseBody
    public RsetBean loginOut(@CookieValue(name = CookieConstant.CK_TOKEN,required = false)String uuid,
                            HttpServletResponse response){
        if(uuid != null){
            //需要删除客户端中的cookie的记录
            Cookie cookie = new Cookie(CookieConstant.CK_TOKEN,"");
            cookie.setPath("/");
            //使cooker失效
            cookie.setMaxAge(0);
            response.addCookie(cookie);
            //删除redis中的凭证 UUID
            return userService.loginOut(uuid);
        }
        return new RsetBean("404","登入失效了！");
    }

}
