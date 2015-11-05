package com.ccb.qd.weblist;

import com.gargoylesoftware.htmlunit.*;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.util.NameValuePair;
import org.apache.commons.logging.LogFactory;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by han on 2015/6/17.
 */
public class GongShenTest {
    public static void testYouku() throws Exception {
        String url = "http://218.57.139.24/securitycode";
          // 模拟一个浏览器
        final WebClient webClient = new WebClient(BrowserVersion.FIREFOX_17);

        LogFactory.getFactory().setAttribute("org.apache.commons.logging.Log","org.apache.commons.logging.impl.NoOpLog");
        java.util.logging.Logger.getLogger("net.sourceforge.htmlunit").setLevel(java.util.logging.Level.OFF);
        webClient.getOptions().setThrowExceptionOnScriptError(false);
        webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
        // final WebClient webClient=new
        // WebClient(BrowserVersion.FIREFOX_10,"http://myproxyserver",8000);
        // //使用代理
        // final WebClient webClient2=new
        // WebClient(BrowserVersion.INTERNET_EXPLORER_10);
        // 设置webClient的相关参数
        webClient.getOptions().setJavaScriptEnabled(true);
        webClient.getOptions().setActiveXNative(false);
        webClient.getOptions().setCssEnabled(false);
        webClient.getOptions().setThrowExceptionOnScriptError(false);
        webClient.waitForBackgroundJavaScript(600*1000);
        webClient.setAjaxController(new NicelyResynchronizingAjaxController());

        webClient.getOptions().setJavaScriptEnabled(true);

        webClient.setAjaxController(new NicelyResynchronizingAjaxController());

        // 模拟浏览器打开一个目标网址
      //  final HtmlPage page = webClient.getPage(url);
        final UnexpectedPage upage  = webClient.getPage(url);

       InputStream is= upage.getInputStream();

        BufferedImage bi1 = ImageIO.read(is);
        File w2 = new File("d://QQ.jpg");//可以是jpg,png,gif格式
        ImageIO.write(bi1, "jpg", w2);//不管输出什么格式图片，此处不需改动

        URL url1 = new URL("http://218.57.139.24/pub/indsearch");

        WebRequest reqSet = new WebRequest (url1,HttpMethod.POST);

        List paramsList = new ArrayList();

        paramsList.add(new NameValuePair("_csrf", "815710a8-2f2a-4173-a6e8-2d1d051d6236"));


        paramsList.add(new NameValuePair("secode", "c4ca4238a0b923820dcc509a6f75849b"));//

        paramsList.add(new NameValuePair("kw", "青岛真源广告有限公司"));//

        reqSet.setRequestParameters(paramsList);

        HtmlPage mypage = (HtmlPage) webClient.getPage(reqSet);

        final HtmlPage page = webClient.getPage(url);
//		该方法在getPage()方法之后调用才能生效
        webClient.waitForBackgroundJavaScript(1000*3);
        webClient.setJavaScriptTimeout(0);
//		Thread.sleep(1000 *3L);
//		String js = "javascript:checkShowFollow('271942','2');";
//		ScriptResult sr = page.executeJavaScript(js);
//		HtmlPage newPage = (HtmlPage) sr.getNewPage();
//		System.out.println("new page.asText=" + newPage.asText());
//		System.out.println("page.asText=" + page.asText());
//		System.out.println("page.getUrl=" + page.getUrl());
        List links = (List) page.getByXPath ("//*[@id=\"groups_tab\"]/div[1]/ul/li[1]/a");
        if(null!=links){
            System.out.println(links.size());
            HtmlAnchor link = (HtmlAnchor) links.get(0);
            System.out.println(link.asXml());
            HtmlPage p = link.click();
            webClient.waitForBackgroundJavaScript(1000*3L);
//			webClient.waitForBackgroundJavaScriptStartingBefore(1000L);
//			Thread.sleep(3000L);
            System.out.println(p.asText());
        }
    }
    public static void main(String[] args){
        try {
          testYouku();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
