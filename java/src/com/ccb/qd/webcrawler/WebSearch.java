package com.ccb.qd.webcrawler;

import com.ccb.qd.frame.MyIe;
import com.gargoylesoftware.htmlunit.*;
import com.gargoylesoftware.htmlunit.html.*;
import com.gargoylesoftware.htmlunit.util.Cookie;
import com.gargoylesoftware.htmlunit.util.NameValuePair;
import org.springframework.beans.factory.annotation.Value;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by han on 2015/7/14.
 */
@org.springframework.stereotype.Component
public abstract class WebSearch implements IWebSearch {
    protected MyIe ie;
    protected WebRequest wb;
    protected com.ccb.qd.frame.ImagePanel imagePanel;
    protected JTextComponent secCode;
    protected JTextComponent searchText;
    protected JList  firmList;
    protected WebClient webClient = null;
    protected HtmlPage rootPage;
    private boolean isProxy=false;
    private int timeout = 50000;
    private String proxyUser ="";
    private String proxyPass="";
    private int  proxyPort = 8080 ;
    private String proxyHost="" ;

    /**
     * Get请求
     * @param url
     * @return
     * @throws Exception
     */
    public static byte[] sendGetRequest(String url) throws Exception{
        WebClient webClient = new WebClient();
        WebRequest webRequest = new WebRequest(new URL(url));
        webRequest.setHttpMethod(HttpMethod.GET);
        return sendRequest(webClient,webRequest);
    }

    /**
     * Post 请求
     *
     * @param params
     * @return
     * @throws Exception
     */
    public static byte[] sendPostRequest(String url,Map<String,String> params) throws Exception{
        WebClient webClient = new WebClient();
        WebRequest webRequest = new WebRequest(new URL(url));
        webRequest.setHttpMethod(HttpMethod.POST);
        if (params != null && params.size() > 0) {
            for (Map.Entry<String, String> param : params.entrySet()) {
                webRequest.getRequestParameters().add(new NameValuePair(param.getKey(), param.getValue()));
            }
        }
        return sendRequest(webClient,webRequest);
    }
    public void getElements(String url,String id,String a) throws Exception {

        final HtmlPage page = webClient.getPage(url);
        final HtmlDivision div = page.getHtmlElementById(id);
        final HtmlAnchor anchor = page.getAnchorByName(a);
    }
    //底层请求
    protected static byte[] sendRequest(WebClient webClient, WebRequest webRequest) throws Exception{
        byte[] responseContent = null;
        Page page = webClient.getPage(webRequest);

        WebResponse webResponse = page.getWebResponse();

        int status = webResponse.getStatusCode();

        System.out.println("Charset : " + webResponse.getContentCharset());

        System.out.println("ContentType : " + webResponse.getContentType());

        // 读取数据内容
        if (status==200) {
            if (page.isHtmlPage()) {
                // 等待JS执行完成，包括远程JS文件请求，Dom处理
                        webClient.waitForBackgroundJavaScript(3000);
                                    // 使用JS还原网页
                        responseContent = ((HtmlPage) page).asXml().getBytes();
            } else {
                InputStream bodyStream = webResponse.getContentAsStream();
              //  responseContent = ByteStream.toByteArray(bodyStream);
                ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream(bodyStream.available());
                responseContent=  byteArrayOutputStream.toByteArray();
                bodyStream.close();
            }
        }
        // 关闭响应流
        webResponse.cleanUp();

        return responseContent;
    }

    public boolean isProxy() {
        return isProxy;
    }

    @Value("${isProxy}")
    public void setProxy(boolean isProxy) {
        this.isProxy = isProxy;
    }

    public String getProxyHost() {
        return proxyHost;
    }

    @Value("${proxyHost}")
    public void setProxyHost(String proxyHost) {
        this.proxyHost = proxyHost;
    }

    public String getProxyPass() {
        return proxyPass;
    }

    @Value("${proxyPass}")
    public void setProxyPass(String proxyPass) {
        this.proxyPass = proxyPass;
    }

    public int getProxyPort() {
        return proxyPort;
    }

    @Value("${proxyPort}")
    public void setProxyPort(int proxyPort) {
        this.proxyPort = proxyPort;
    }

    public String getProxyUser() {
        return proxyUser;
    }

    @Value("${proxyUser}")
    public void setProxyUser(String proxyUser) {
        this.proxyUser = proxyUser;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public void homePage_Firefox() throws Exception {
        webClient = new WebClient(BrowserVersion.FIREFOX_24);
        webClient.getOptions().setJavaScriptEnabled(true);
        webClient.getOptions().setCssEnabled(false);

        webClient.setAjaxController(new NicelyResynchronizingAjaxController());
        webClient.getOptions().setTimeout(35000);
        webClient.getOptions().setThrowExceptionOnScriptError(false);
       // final HtmlPage page = webClient.getPage(Url);
     //   assertEquals("HtmlUnit - Welcome to HtmlUnit", page.getTitleText());
    }

    public void xpath() throws Exception {
        final WebClient webClient = new WebClient();
        final HtmlPage page = webClient.getPage("http://htmlunit.sourceforge.net");

        //get list of all divs
        final java.util.List<?> divs = page.getByXPath("//div");

        //get div which has a 'name' attribute of 'John'
        final HtmlDivision div = (HtmlDivision) page.getByXPath("//div[@name='John']").get(0);
    }

    public void homePage_proxy() throws Exception {

         webClient = new WebClient(BrowserVersion.FIREFOX_24, proxyHost, proxyPort);

        //set proxy username and password
        final DefaultCredentialsProvider credentialsProvider = (DefaultCredentialsProvider) webClient.getCredentialsProvider();
       // credentialsProvider.addProxyCredentials("username", "password");
        credentialsProvider.addCredentials(proxyUser, proxyPass);
        webClient.getOptions().setJavaScriptEnabled(true);
        webClient.getOptions().setCssEnabled(false);

        webClient.setAjaxController(new NicelyResynchronizingAjaxController());
        webClient.getOptions().setTimeout(35000);
        webClient.getOptions().setThrowExceptionOnScriptError(false);
   //     final HtmlPage page = webClient.getPage(url);
      //  assertEquals("HtmlUnit - Welcome to HtmlUnit", page.getTitleText());
    }

    public void submittingForm() throws Exception {


        // Get the first page
        final HtmlPage page1 = webClient.getPage("http://some_url");

        // Get the form that we are dealing with and within that form,
        // find the submit button and the field that we want to change.
        final HtmlForm form = page1.getFormByName("myform");

        final HtmlSubmitInput button = form.getInputByName("submitbutton");
        final HtmlTextInput textField = form.getInputByName("userid");

        // Change the value of the text field
        textField.setValueAttribute("root");

        // Now submit the form by clicking the button and get back the second page.
        final HtmlPage page2 = button.click();
    }

    @SuppressWarnings("unused")
    private Map<String, String> getResponseCookies() {
        Set<Cookie> cookies = webClient.getCookieManager().getCookies();
        Map<String, String> responseCookies =  new HashMap<String, String>();
        for (Cookie c : cookies) {
            responseCookies.put(c.getName(), c.getValue());
        }
        return responseCookies;
    }

    @SuppressWarnings("unused")
    private void setCookies(String domain, Map<String, String> cookies) {
        if (cookies != null && cookies.size() > 0) {
            webClient.getCookieManager().setCookiesEnabled(true);// enable
            // cookies
            for (Map.Entry<String, String> c : cookies.entrySet()) {
                Cookie cookie = new Cookie(domain, c.getKey(), c.getValue());
                webClient.getCookieManager().addCookie(cookie);

                System.out.println("Set Cookies : " + c.getKey() + " | " + c.getValue());
            }
        }
    }

    /**
     * 清除所有cookie
     */
    public void clearCookies() {
        webClient.getCookieManager().clearCookies();
    }

    @WebStep("9")
    public void shutdown() throws IOException {
        webClient.closeAllWindows();
    }

    protected static class ImagePanel extends JPanel {

        private BufferedImage image;

        public ImagePanel() {

        }
       public ImagePanel(BufferedImage b) {

               image = b;

       }
        @Override
        public void paintComponent(Graphics g) {
            g.drawImage(image, 0, 0, null);
        }

    }
}
