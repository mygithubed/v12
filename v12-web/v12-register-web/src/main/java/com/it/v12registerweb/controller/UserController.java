package com.it.v12registerweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Author:曾志鹏
 * Date:2019/6/24
 * Time:21:08
 */

@RequestMapping("user")
@Controller
public class UserController {

    @RequestMapping("register")
    public String toRegister(){

        return "register";
    }
}
