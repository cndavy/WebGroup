package com.ccb.qd.webcrawler;

import com.ccb.qd.frame.MyIe;
import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by han on 2015/7/14.
 */
@Component
public class HongdunWebSearch extends WebSearch  {
    private  com.ccb.qd.frame.ImagePanel   imagePanel;
    private  JTextComponent secCode;
    private  JTextComponent searchText;
    private  JList list1;
    private  MyIe ie;

    public HongdunWebSearch() {
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
            wb = new WebRequest(new URL("http://www.ubaike.cn/"));
            wb.setCharset("UTF-8");
            sendRequest(webClient, wb);
            rootPage = webClient.getPage(wb);
            wb.setCharset("UTF-8");
            webClient.getHTMLParserListener();
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
    //    rootPage.executeJavaScript(" $(\"#bdcs-search-form-input\").val(\""+inputText+"\")");
 rootPage.getForms();
        // Get the form that we are dealing with and within that form,
        // find the submit button and the field that we want to change.
        final HtmlForm form = rootPage.getForms().get(0);
     //   rootPage.executeJavaScript(" $(\"#bdcs-search-form-input\").val(\""+inputText+"\")");
        ;
        final HtmlSubmitInput button = (HtmlSubmitInput)form.getByXPath("//input[@type=\"submit\"]").get(0);
       final HtmlTextInput textField = form.getInputByName("q");

        // Change the value of the text field
        textField.setValueAttribute(inputText);

        // Now submit the form by clicking the button and get back the second page.
        final HtmlPage page2 = button.click();
       page2.asXml();
        page2.getByXPath("//a[@href]/text()") ;
    }
    @Override
    public void TransParam(com.ccb.qd.frame.ImagePanel imagePanel, JTextComponent secCode, JTextComponent searchText, JList list1, MyIe ie) {
        this.imagePanel = imagePanel;
        this.secCode = secCode;
        this.searchText = searchText;
        this.list1 = list1;
        this.ie = ie;
    }
}
