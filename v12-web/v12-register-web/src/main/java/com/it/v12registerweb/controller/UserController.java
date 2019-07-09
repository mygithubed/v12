package com.it.v12registerweb.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.it.v12.api.IUserService;
import com.it.v12.common.constant.RabbitMQConstant;
import com.it.v12.entity.TUser;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.annotation.Resource;
import java.util.*;

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

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    /**
     * 前往注册的页面
     * @return 返回到注册页面
     */
    @RequestMapping("register")
    public String toRegister(){
        return "register";
    }

    /**
     *  注册用户信息保存
     * @param user 注册用户填写的信息
     * @return 注册的状态
     */
    @RequestMapping("add")
    public String register(TUser user){
        //设置flag为1 表示没有删除
        user.setFlag(true);
        //设置创建时间和修改时间
        user.setRegistDate(new Date());
        user.setLastLoginDate(new Date());
        //邮箱注册的手机号为空 设置为0
        user.setPhone("0");

        /**
                //先获取用户输入的密码
                String password = user.getPassword();
                //给密码加密
                String encode = bCryptPasswordEncoder.encode(password);
                user.setPassword(encode);
        **/

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
            //收件人的邮箱
            map.put("toAddress",user.getEmail());
            //邮件的标题
            map.put("subject","疯狂购物商城的激活邮件");
            //邮件的内容
            map.put("text",text);
            //发送消息到交换机上
            rabbitTemplate.convertAndSend(RabbitMQConstant.REGISTER_EXCHAGE,"user_add",map);
        }
        //添加成功后前往登录页面，进行登入操作
        return "redirect:http://localhost:9095/sso/tologin";
    }

    /**
     * 邮件激活用户
     * @param uuid  激活码
     * @return 进入激活页面
     */
    @RequestMapping("activer/{uuid}")
    public String activerUser(@PathVariable String uuid){
        //在redis中查出对应的用户ID
        Long id = Long.parseLong(String.valueOf(redisTemplate.opsForValue().get(uuid)));
        //通过ID来修改用户的状态，激活用户
        userService.updateStart(id);
        return "activer";
    }

    /**
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("getById/{id}")
    public TUser getUserById(@PathVariable Long id){
        TUser tUser = userService.selectByPrimaryKey(id);
        return tUser;
    }

    @ResponseBody
    @RequestMapping("toSendSms/{phoneNumber}")
    public String SendSms(@PathVariable String phoneNumber){


        System.out.println(phoneNumber);


        //生成4位数的随机数
        String code = String.valueOf((int)((Math.random()*9+1)*1000));

        //在Redis中保存验证码
        redisTemplate.opsForValue().set("user:code",code);

        /**发送消息到交换机**/
        Map<String,String> map = new HashMap<>();
        //手机号
        map.put("toPhone",phoneNumber);
        //验证码
        map.put("code",code);
        //发送消息到交换机上
        rabbitTemplate.convertAndSend(RabbitMQConstant.REGISTER_EXCHAGE,"user_add_phone",map);

        return "200";
    }


    @RequestMapping("addByPhone")
    public String addByPhone(TUser user){
        //设置flag为1 表示没有删除
        user.setFlag(true);
        //设置创建时间和修改时间
        user.setRegistDate(new Date());
        user.setLastLoginDate(new Date());
        //邮箱注册的手机号为空 设置为0
        user.setEmail("0");
        int id = userService.insertSelective(user);
        if(id>0){

        }
        return  "";
    }

}
