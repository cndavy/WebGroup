package com.ccb.qd.weblist;

import com.gargoylesoftware.htmlunit.*;
import com.gargoylesoftware.htmlunit.html.*;
import com.gargoylesoftware.htmlunit.util.Cookie;
import com.gargoylesoftware.htmlunit.util.NameValuePair;
import org.springframework.beans.factory.annotation.Value;

import javax.imageio.ImageReader;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.*;
import java.util.List;

/**
 * Created by han on 2015/6/17.
 */
public class HomePage {
    public HomePage() {


    }

    private WebClient webClient = null;

    private boolean isProxy=false;

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

    private int timeout = 50000;

    private String proxyUser ="";

    private String proxyPass="";

    private int  proxyPort = 8080 ;

    private String proxyHost="" ;
    public void homePage_Firefox(String Url) throws Exception {
        final WebClient webClient = new WebClient(BrowserVersion.FIREFOX_24);
        webClient.getOptions().setJavaScriptEnabled(true);
        webClient.getOptions().setCssEnabled(false);

        webClient.setAjaxController(new NicelyResynchronizingAjaxController());
        webClient.getOptions().setTimeout(35000);
        webClient.getOptions().setThrowExceptionOnScriptError(false);
        final HtmlPage page = webClient.getPage(Url);
     //   assertEquals("HtmlUnit - Welcome to HtmlUnit", page.getTitleText());
    }
    public void getElements() throws Exception {

        final HtmlPage page = webClient.getPage("http://htmlunit.sourceforge.net");
        final HtmlDivision div = page.getHtmlElementById("some_div_id");
        final HtmlAnchor anchor = page.getAnchorByName("anchor_name");
    }
        public void xpath() throws Exception {
            final WebClient webClient = new WebClient();
            final HtmlPage page = webClient.getPage("http://htmlunit.sourceforge.net");

            //get list of all divs
            final List<?> divs = page.getByXPath("//div");

            //get div which has a 'name' attribute of 'John'
            final HtmlDivision div = (HtmlDivision) page.getByXPath("//div[@name='John']").get(0);
        }
        public void homePage_proxy(String url) throws Exception {

            final WebClient webClient = new WebClient(BrowserVersion.FIREFOX_24, proxyHost, proxyPort);

            //set proxy username and password
            final DefaultCredentialsProvider credentialsProvider = (DefaultCredentialsProvider) webClient.getCredentialsProvider();
           // credentialsProvider.addProxyCredentials("username", "password");
            credentialsProvider.addCredentials(proxyUser, proxyPass);
            webClient.getOptions().setJavaScriptEnabled(true);
            webClient.getOptions().setCssEnabled(false);

            webClient.setAjaxController(new NicelyResynchronizingAjaxController());
            webClient.getOptions().setTimeout(35000);
            webClient.getOptions().setThrowExceptionOnScriptError(false);
            final HtmlPage page = webClient.getPage(url);
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

    //底层请求
    private static byte[] sendRequest(WebClient webClient,WebRequest webRequest) throws Exception{
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
                        webClient.waitForBackgroundJavaScript(10000);
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

    public void shutdown() throws IOException {
        webClient.closeAllWindows();
    }

    public static void main(String[] args){
        try {
            final WebClient webClient = new WebClient(BrowserVersion.FIREFOX_24);
            webClient.getOptions().setJavaScriptEnabled(true);
            webClient.getOptions().setCssEnabled(false);

            webClient.setAjaxController(new NicelyResynchronizingAjaxController());
            webClient.getOptions().setTimeout(35000);
            webClient.getOptions().setThrowExceptionOnScriptError(false);

            // final HtmlPage rootPage = webClient.getPage("http://www.ccb.com/cn/jump/personal_loginbank.html");
            //   HtmlSelect hs = (HtmlSelect) rootPage.getElementById("lstCubes");
            WebRequest wb=new WebRequest(new URL("http://218.57.139.24/"));
            wb.setCharset("UTF-8");
            sendRequest(webClient,wb);
            final HtmlPage rootPage =    webClient.getPage(wb );
            wb.setCharset("UTF-8");
            webClient.getHTMLParserListener();
//get sec code image
            rootPage.executeJavaScript(" $(\"#searchtxt\").val(\"青岛真源广告有限公司\")");

            rootPage.executeJavaScript(" zdm()");
            List listimg=   rootPage.getByXPath("//*[@id=\"secimg\"]");
            HtmlImage    secimg= (HtmlImage) listimg.get(0);
            ImageReader imageReader = secimg.getImageReader();
            final BufferedImage bufferedImage = imageReader.read(0);
            JFrame jframe = new JFrame();
            jframe.add(new ImagePanel(bufferedImage));
            jframe.setSize(400, 300);
            jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            jframe.setVisible(true);
            Thread.sleep(1000);
          /*  File w2 = new File("d://QQ.jpg");//可以是jpg,png,gif格式
            ImageIO.write(bufferedImage, "jpg", w2);//不管输出什么格式图片，此处不需改动
*/

            System.out.println(">>" );
            Scanner sc=new Scanner(System.in);
            int i=sc.nextInt();

            String secStr=String.valueOf(i); //会从控制台读入一行字符串，以回车为标志
            System.out.println(">>"+secStr);

            ScriptResult  yzminput= rootPage.executeJavaScript(" $(\"#yzminput\").val(\""+secStr+"\");");
           // ScriptResult     secode=  rootPage.executeJavaScript(" $(\"#secode\").val(toMD5Str(\""+secStr+"\"));");

          //  ScriptResult     csrf=  rootPage.executeJavaScript(" $(\"meta[name='_csrf']\").attr(\"content\")");
        //    System.out.println(rootPage.executeJavaScript(" $(\"#searchtxt\").val()").toString());

            ScriptResult nextP= rootPage.executeJavaScript(" searching()");
          //  rootPage.getByXPath("//*[name=\"kw\"]");
           HtmlPage showP=( HtmlPage) nextP.getNewPage();
         //   showP.executeJavaScript(" $(\"#searchtxt\").val(\"青岛真源广告有限公司\")");
           // showP.executeJavaScript(" zdm()");
        //    showP.executeJavaScript(" $(\"#searchtxt\").val()");
           HtmlDivision listdiv= ( HtmlDivision)showP.getByXPath("//*[@class=\"list\"]").get(0);
            listdiv.getAttributesMap();
            listdiv.getAttributes();
            listdiv.asXml();
           HtmlAnchor nextA=(HtmlAnchor) listdiv.getByXPath("./ul/li/a").get(0);
            nextA.getAttribute("href");
            wb=new WebRequest(new URL("http://218.57.139.24/pub/"+ nextA.getAttribute("href")));
            System.out.println("正在跳转…");

            HtmlPage mypage = (HtmlPage) webClient.getPage(wb);
            webClient.waitForBackgroundJavaScript(1000*3);
            webClient.setJavaScriptTimeout(0);
            HtmlPage mypage2 = (HtmlPage)  mypage.executeJavaScript("togo('2');").getNewPage();
            webClient.waitForBackgroundJavaScript(1000*3);
            webClient.setJavaScriptTimeout(0);
            mypage2.asXml();

            HtmlPage mypage3 = (HtmlPage)  mypage.executeJavaScript("togo('3');").getNewPage();
            webClient.waitForBackgroundJavaScript(1000*3);
            webClient.setJavaScriptTimeout(0);
            mypage3.asXml();
            HtmlPage mypage4 = (HtmlPage)  mypage.executeJavaScript("togo('4');").getNewPage();
            webClient.waitForBackgroundJavaScript(1000*3);
            webClient.setJavaScriptTimeout(0);
            mypage4.asXml();


            webClient.waitForBackgroundJavaScript(1000*3);
            webClient.setJavaScriptTimeout(0);
           new String( mypage.asXml().getBytes(),"UTF-8");
            mypage.getByXPath("//*[@id=\"mct\"]");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
   private static class ImagePanel extends JPanel {

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
