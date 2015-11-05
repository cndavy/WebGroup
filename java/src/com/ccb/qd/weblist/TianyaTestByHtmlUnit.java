package com.ccb.qd.weblist;

/**
 * Created by han on 2015/6/17.
 */

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlButton;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;


/**

 * @author CaiBo

 * @date 2014年9月15日 上午9:16:36 

 * @version $Id$

 *

 */


/*
执行上面这个Main函数你会得到一下的结果：

        我们看到，响应码确实是200，表明成功了，其实这个响应相当于是302，它是需要跳转的，只不过它的跳转写到了body部分的js里面而已。

<script>

location.href="http://passport.tianya.cn:80/online/loginSuccess.jsp?fowardurl=http%3A%2F%2Fwww.tianya.cn%2F94693372&userthird=&regOrlogin=%E7%99%BB%E5%BD%95%E4%B8%AD……&t=1410746182629&k=8cd4d967491c44c5eab1097e0f30c054&c=6fc7ebf8d782a07bb06624d9c6fbbf3f";

</script>

        它这是一个页面上的跳转

        那这个时候如果你使用HttpClient就头疼了（当然也是可以处理的，后面讲到）。如果你使用的是HtmlUnit，整个过程显得简单轻松。
*/

public class TianyaTestByHtmlUnit {

    public static void main(String[] args) throws Exception {

        WebClient webClient = new WebClient();

// 拿到这个网页 

        HtmlPage page = webClient.getPage("http://passport.tianya.cn/login.jsp");

// 填入用户名和密码 

        HtmlInput username = (HtmlInput) page.getElementById("userName");

        username.type("ifugletest2014");

        HtmlInput password = (HtmlInput) page.getElementById("password");

        password.type("test123456");

// 提交 

        HtmlButton submit = (HtmlButton) page.getElementById("loginBtn");

        HtmlPage nextPage = submit.click();

        System.out.println(nextPage.asXml());

    }

} 