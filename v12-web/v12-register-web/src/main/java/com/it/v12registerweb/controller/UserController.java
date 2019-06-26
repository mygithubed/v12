package com.it.v12registerweb.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.it.v12.api.IUserService;
import com.it.v12.common.constant.RabbitMQConstant;
import com.it.v12.entity.TUser;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author:曾志鹏
 * Date:2019/6/24
 * Time:21:08
 */


@Controller
@RequestMapping("user")
public class UserController {


    @Reference
    private IUserService userService;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private TemplateEngine templateEngine;

    @Resource(name="stringReadis")
    private RedisTemplate<String,Object> redisTemplate;

    /**
     * 前往注册的页面
     * @return
     */
    @RequestMapping("register")
    public String toRegister(){
        return "register";
    }


    @RequestMapping("add")
    public String register(TUser user){
        user.setFlag(true);
        int id = userService.insertSelective(user);
        System.out.println(user);
        if(id>0){
            //模板的生成
            Context context = new Context();
            context.setVariable("username",user.getUsername());
            String uuid = UUID.randomUUID().toString();
            String url =  new StringBuilder("http://localhost:9094/user/activer/").append(uuid).toString();
            context.setVariable("url",url);
            String text = templateEngine.process("email.html",context);

            //在Redis中保存UUId
            redisTemplate.opsForValue().set(uuid,id+"");

            /**发送消息到交换机**/
            Map<String,String> map = new HashMap<>();
            map.put("toAddress",user.getEmail());
            map.put("subject","疯狂购物商城的激活邮件");
            map.put("text",text);
            rabbitTemplate.convertAndSend(RabbitMQConstant.REGISTER_EXCHAGE,"user_add",map);
        }
        return "login";
    }

    @RequestMapping("activer/{uuid}")
    public String activerUser(@PathVariable String uuid){
        //在redis中查出对应的用户ID
        Long id = Long.parseLong(String.valueOf(redisTemplate.opsForValue().get(uuid)));
        //通过ID来修改用户的状态，激活用户
        userService.updateStart(id);
        return "activer";
    }
    @ResponseBody
    @RequestMapping("getById/{id}")
    public TUser getUserById(@PathVariable Long id){
        TUser tUser = userService.selectByPrimaryKey(id);
        return tUser;
    }

}
