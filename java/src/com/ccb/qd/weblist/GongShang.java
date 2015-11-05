package com.ccb.qd.weblist;

/**
 * Created by han on 2015/6/17.
 */

import org.apache.http.*;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**

 * @author CaiBo

 * @date 2014年9月15日 上午9:16:36 

 * @version $Id$

 *

 */

public class GongShang {

    public static void main(String[] args) throws Exception {



        CloseableHttpClient webClient=    HttpClients.custom().build();
        HttpPost post = getPost();


        HttpResponse hr = webClient.execute(post);
       showResponseInfo(hr);
        HttpEntity  he=   hr.getEntity();
 he.writeTo(new FileOutputStream( new File("d:\\1.txt")

 ));
        HttpResponse  r= hr;
    }
    private static void showResponseInfo(HttpResponse hr) throws ParseException, IOException {

        System.out.println("响应状态行信息：" + hr.getStatusLine());

        System.out.println("—————————————————————");

        System.out.println("响应头信息：");

        Header[] allHeaders = hr.getAllHeaders();

        for (int i = 0; i < allHeaders.length; i++) {

            System.out.println(allHeaders[i].getName() + ":" + allHeaders[i].getValue());

        }

        System.out.println("—————————————————————");

        System.out.println("响应正文：");

        System.out.println( (hr.getEntity()));


    }
    private static HttpPost getPost() {

        HttpPost post = new HttpPost("http://218.57.139.24/pub/indsearch");

// 首先我们初始化请求头
        post.addHeader("Referer",    "http://218.57.139.24/");
        post.addHeader("Host", "218.57.139.24");

        post.addHeader("User-Agent", "" +
                "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:38.0) Gecko/20100101 Firefox/38.0");
        post.addHeader("Accept-Language","zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3");
        post.addHeader("Cookie","JSESSIONID=CZiI6yE6hzfd0W7bcCYjziwb.undefined");


// 然后我们填入我们想要传递的表单参数（主要也就是传递我们的用户名和密码）

// 我们可以先建立一个List，之后通过post.setEntity方法传入即可

// 写在一起主要是为了大家看起来方便，大家在正式使用的当然是要分开处理，优化代码结构的

        List<NameValuePair> paramsList = new ArrayList<NameValuePair>();

/*

* 添加我们要的参数，这些可以通过查看浏览器中的网络看到，如下面我的截图中看到的一样

* 不论你用的是firebut,httpWatch或者是谷歌自带的查看器也好,都能查看到（后面会推荐辅助工具来查看）

* 要把表单需要的参数都填齐，顺序不影响

*/

        paramsList.add(new BasicNameValuePair("_csrf", "815710a8-2f2a-4173-a6e8-2d1d051d6236"));


        paramsList.add(new BasicNameValuePair("secode", "c4ca4238a0b923820dcc509a6f75849b"));//

        paramsList.add(new BasicNameValuePair("kw", "青岛真源广告有限公司"));//

// 将这个参数list设置到post中

        post.setEntity(new UrlEncodedFormEntity(paramsList, Consts.UTF_8));

        return post;

    }
} 