package com.ccb.qd.webcrawler;

import com.ccb.qd.frame.MyIe;
import com.gargoylesoftware.htmlunit.*;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlDivision;
import com.gargoylesoftware.htmlunit.html.HtmlImage;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.springframework.stereotype.Component;

import javax.imageio.ImageReader;
import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Scanner;

/**
 * Created by han on 2015/6/17.
 */
@Component
public class GongShangWebSearch extends WebSearch {

    public GongShangWebSearch() {
    }
   @Override
   public void TransParam(com.ccb.qd.frame.ImagePanel imagePanel, JTextComponent secCode, JTextComponent searchText, JList list1, MyIe ie){
       this.imagePanel=imagePanel;
       this.secCode=secCode;
       this.searchText=searchText;
       this.firmList=list1;
       this.ie=ie;
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

    @WebStep("init")
    public void run() {
        try {
           if (webClient==null) {
               if (isProxy()) {
                   homePage_proxy();
               } else {
                   homePage_Firefox();
               }
           }
            wb = null;
                wb = new WebRequest(new URL("http://218.57.139.24/"));
            wb.setCharset("UTF-8");
                sendRequest(webClient, wb);
             rootPage = webClient.getPage(wb);
            wb.setCharset("UTF-8");
            webClient.getHTMLParserListener();
//get sec code image



        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @WebStep("search")
    public void runGetText() throws IOException {
        if (webClient==null){run();}
            String inputText= searchText.getText();
        System.out.println(inputText);
        rootPage.executeJavaScript(" $(\"#searchtxt\").val(\""+inputText+"\")");
      //  ScriptResult     secode=  rootPage.executeJavaScript(" $(\"#secode\").val(toMD5Str(\""+inputText+"\"));");
        rootPage.executeJavaScript(" zdm()");
        List listimg = rootPage.getByXPath("//*[@id=\"secimg\"]");
        HtmlImage secimg = (HtmlImage) listimg.get(0);
        ImageReader imageReader = secimg.getImageReader();
        final BufferedImage bufferedImage = imageReader.read(0);
        imagePanel.setImage(bufferedImage);
    //    imagePanel.getGraphics().drawImage(bufferedImage, 0, 0, null);
        imagePanel.revalidate();
        imagePanel.paint(imagePanel.getGraphics());
    }
@WebStep("afterSec")
public void run2() throws IOException {
    String inputText= searchText.getText();
    if (inputText==null||inputText.length()==0){
        this.searchText.requestFocus();
        return;
    }
        String secStr=secCode.getText();

        ScriptResult  yzminput= rootPage.executeJavaScript(" $(\"#yzminput\").val(\""+secStr+"\");");

        ScriptResult nextP= rootPage.executeJavaScript(" searching()");

        HtmlPage showP=( HtmlPage) nextP.getNewPage();

        HtmlDivision listdiv= ( HtmlDivision)showP.getByXPath("//*[@class=\"list\"]").get(0);
        List <HtmlDivision> listOfFirm=(List <HtmlDivision>)showP.getByXPath("//*[@class=\"list\"]");
       firmList.clearSelection();
            DefaultListModel dlm = new DefaultListModel();
            for (HtmlDivision h : listOfFirm){
         //   h.asText();
            HtmlAnchor nextA=(HtmlAnchor) h.getByXPath("./ul/li/a").get(0);
            dlm.addElement( nextA.asText()+"|"+nextA.getAttribute("href"));
            }
           firmList.setModel(dlm);


}
    @WebStep("clickList")
    public void  clickList() throws IOException {
      int l= firmList.getSelectedIndex();
        String strl=(String )firmList.getModel().getElementAt(l);

        String str= strl.split("\\|",0)[1];
        wb=new WebRequest(new URL("http://218.57.139.24/pub/"+str));

        HtmlPage mypage = (HtmlPage) webClient.getPage(wb);
        webClient.waitForBackgroundJavaScript(1000*3);
        webClient.setJavaScriptTimeout(0);

       // mypage.getByXPath("//*[@id=\"mct\"]");
        //mypage.getByXPath("//*[@id=\"jibenxinxi\"]");
        ie.getEditPanel().setPage("http://218.57.139.24/pub/"+str);
         ie.getEditPanel().setVisible(true);
        // ie.getEditPanel().setEnabled(false);
        ie.getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ie.getFrame().setVisible(true);

    }
    public void ShowIe(){
        JEditorPane je=ie.getEditPanel();

    }

}
