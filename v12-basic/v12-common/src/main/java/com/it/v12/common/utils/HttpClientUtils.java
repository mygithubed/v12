package com.it.v12.common.utils;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
/**
 * 工具类
 * @author:曾志鹏
 * Date:2019/6/18
 * Time:21:17
 */
public class HttpClientUtils {
    /**
     * 携带请求参数的GET请求
     *
     * @param url
     * @param param
     * @return
     */
    public static String doGet(String url, Map<String, String> param) {
        //
        String result = "";
        // 1.创建Httpclient对象
        CloseableHttpClient httpclient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        try {
            // 2.创建uri对象
            URIBuilder builder = new URIBuilder(url);
            if (param != null) {
                for (String key : param.keySet()) {
                    builder.addParameter(key, param.get(key));
                }
            }
            URI uri = builder.build();
            // 3.创建http GET请求
            HttpGet httpGet = new HttpGet(uri);
            // 4.执行请求
            response = httpclient.execute(httpGet);
            // 5.判断返回状态是否为200
            if (response.getStatusLine().getStatusCode() == 200) {
                // 6.进行UTF-8编码处理
                result = EntityUtils.toString(response.getEntity(), "UTF-8");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
    /**
     * 不需要携带参数的get请求
     *
     * @param url
     * @return
     */
    public static String doGet(String url) {
        return doGet(url, null);
    }
    /**
     * 携带请求参数的POST请求
     *
     * @param url
     * @param param
     * @return
     */
    public static String doPost(String url, Map<String, String> param) {
        //
        String result = "";
        // 1.创建Httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        try {
            // 2.创建Http Post请求
            HttpPost httpPost = new HttpPost(url);
            // 3.创建参数列表
            if (param != null) {
                List<NameValuePair> paramList = new ArrayList<NameValuePair>();
                for (String key : param.keySet()) {
                    paramList.add(new BasicNameValuePair(key, param.get(key)));
                }
                // 4.模拟表单
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramList);
                httpPost.setEntity(entity);
            }
            // 5.执行http请求
            response = httpClient.execute(httpPost);
            // 6.获取响应的结果
            result = EntityUtils.toString(response.getEntity(), "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
    /**
     * 发送无携带请求参数的POST请求
     *
     * @param url
     * @return
     */
    public static String doPost(String url) {
        return doPost(url, null);
    }
    /**
     * 以json的方式传递请求参数，发送POST请求
     *
     * @param url
     * @param json
     * @return
     */
    public static String doPostJson(String url, String json) {
        //
        String result = "";
        // 1.创建Httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        try {
            // 2.创建Http Post请求
            HttpPost httpPost = new HttpPost(url);
            // 3.创建请求内容
            StringEntity entity = new StringEntity(json, ContentType.APPLICATION_JSON);
            httpPost.setEntity(entity);
            // 4.执行http请求
            response = httpClient.execute(httpPost);
            // 5.获取响应结果
            result = EntityUtils.toString(response.getEntity(), "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
