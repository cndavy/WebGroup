package com.ccb.qd.common;

/**
 * Created by IntelliJ IDEA.
 * User: hantongchao
 * Date: 12-8-3
 * Time: 上午9:22
 * To change this template use File | Settings | File Templates.
 */
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

    /**
     * 读取properties配置文件
     * @author using
     */
    public class Env extends Properties {
        private static  Env instance;

        public static Env getInstance () {
            if(null != instance) {
                return instance;
            } else {
                makeInstance();
                return instance;
            }
        }

        private static synchronized void makeInstance() {
            if(instance == null) {
                instance = new Env();
            }
        }

        private Env() {
            InputStream is = getClass().getResourceAsStream("/global.properties");
            try {
                load(is);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        public static void main(String args[]){
          System.out.println(Env.getInstance().getProperty("han"));  ;//调用

        }
    }

