package com.ccb.qd;

import com.ccb.qd.frame.webGroupFrame;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

import javax.swing.*;
import java.awt.*;

/**
 * Created by han on 2015/6/18.
 */
@Configuration
/*@ConfigurationProperties(locations = "classpath:pbank.properties", ignoreUnknownFields = false)*/
/*@ComponentScan(basePackages="com.ccb.qd")*/

@ImportResource("classpath:springConfig.xml")
@EnableAutoConfiguration

public class springtest {


    public static void main(String []args){

      /*  System.exit(
                SpringApplication.exit(
                        SpringApplication.run(
                                springtest.class, args)));*/
    final  ApplicationContext cf=new AnnotationConfigApplicationContext(springtest.class);
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                webGroupFrame jf = (webGroupFrame )cf.getBean("webGroupFrame");
                jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                jf.setVisible(true);
            }
        });;
    }
}
