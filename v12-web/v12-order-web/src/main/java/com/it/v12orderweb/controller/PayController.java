package com.it.v12orderweb.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.it.v12orderweb.pojo.PayConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import sun.security.util.Length;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author :曾志鹏
 * Date:2019/7/2
 * Time:12:57
 */
@Controller
@RequestMapping("order")
public class PayController {

    @Autowired
    private AlipayClient alipayClient;

    @Value("${alipay.publicKey}")
    private  String publicKey;

    @RequestMapping("pay")
    public void pay(HttpServletRequest httpRequest,String orderNum,
                       HttpServletResponse httpResponse) throws ServletException, IOException {
        //获得初始化的AlipayClient

        //创建API对应的request
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl("http://domain.com/CallBack/return_url.jsp");
        //在公共参数中设置回跳和通知地址
        alipayRequest.setNotifyUrl("http://upd7fr.natappfree.cc/order/get");

        String total_num ="8888";
        String subject ="华为笔记本";
        String body ="私人定制版笔记本";

        PayConfig payConfig = new PayConfig(orderNum,"FAST_INSTANT_TRADE_PAY",total_num,subject,body);
        //转换为json
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(payConfig);

        alipayRequest.setBizContent(json);
        //填充业务参数
        String form = "";
        try {
            //调用SDK生成表单
            form = alipayClient.pageExecute(alipayRequest).getBody();
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        httpResponse.setContentType("text/html;charset=" + "utf-8");
        //直接将完整的表单html输出到页面
        httpResponse.getWriter().write(form);
        httpResponse.getWriter().flush();
        httpResponse.getWriter().close();

    }


    @RequestMapping("get")
    public String gets(HttpServletRequest request,HttpServletResponse response) throws AlipayApiException {
        System.out.println("返回OK了！");
        //sdk需要的参数类型
        Map<String,String> paramsMap = new HashMap<>();
        //将异步通知中收到的所有参数都存放到map中
        Map<String, String[]> parameterMap = request.getParameterMap();
        Set<Map.Entry<String, String[]>> entries = parameterMap.entrySet();
        for (Map.Entry<String, String[]> entry : entries) {
            //构建参数
            StringBuilder  value = new StringBuilder();
            String[] sourArray = entry.getValue();
            for (int i = 0; i < sourArray.length-1; i++) {
                value.append(sourArray[i]).append(",");
            }
            value.append(sourArray[sourArray.length-1]);
            //将数据保存到parameterMap 中去
            paramsMap.put(entry.getKey(),value.toString());
        }

        //调用SDK验证签名
        boolean signVerified = AlipaySignature.rsaCheckV1(paramsMap,publicKey,"utf-8","RSA2");

        if(signVerified){
            // TODO
            // 验签成功后，按照支付结果异步通知中的描述，对支付结果中的业务内容进行二次校验，
            //校验成功后在response中返回success并继续商户自身业务处理，校验失败返回failure


            //原订单号
            String out_trade_no = request.getParameter("out_trade_no");
            //获取订单的金额
            String parameter = request.getParameter("total_amount");

            System.out.println(out_trade_no);
            System.out.println(parameter);
            //与本地的数据库进行比对
            //是否正确
            System.out.println("OK");


          //获取支付宝回来的订单金额和订单号
        }else{
            // TODO
            // 验签失败则记录异常日志，并在response中返回failure.

            //记录支付宝的订单号 ，为了方便对账。。
        }

        return "hello";
    }
}
