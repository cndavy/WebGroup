package com.ccb.qd.common;

/**
 * Created by han on 2015/6/18.
 */
public class AesUtils {
    public static String decrypt(String s, String encryptedString)   {
        try {

            return    ThreeDES.decryptMode(encryptedString );
        } catch (Exception e) {

            e.printStackTrace();
            return  encryptedString;
        }

    }

    public static String encrypt(String s, String value)  {
        try {
            return   ThreeDES.encryptMode(value );
        } catch (Exception e) {
            e.printStackTrace();
            return  value;
        }
    }
}
