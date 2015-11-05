package com.ccb.qd.weblist;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.apache.commons.logging.LogFactory;

import java.util.List;

/**
 * Created by han on 2015/6/17.
 */
public class testYouku {
    public static void testYouku() throws Exception {
        String url = "http://v.youku.com/v_show/id_XNDc2MDkzMTIw.html";
        String xurl = "http://v.youku.com/v_vpofficiallistv5/id_119023280_showid_271942_page_2?__rt=1&__ro=listitem_page2";
        // String a = "<a page=\"2\">178-101</a>";
        // String url="http://www.baidu.com";
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
		/*
		webClient.setJavaScriptTimeout(3600*1000);
		webClient.getOptions().setRedirectEnabled(true);
		webClient.getOptions().setThrowExceptionOnScriptError(true);
		webClient.getOptions().setThrowExceptionOnFailingStatusCode(true);
		webClient.getOptions().setTimeout(3600*1000);
		webClient.waitForBackgroundJavaScript(600*1000);
		*/
//		webClient.waitForBackgroundJavaScript(600*1000);
        webClient.setAjaxController(new NicelyResynchronizingAjaxController());

        // 模拟浏览器打开一个目标网址
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
