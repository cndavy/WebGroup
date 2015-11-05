package com.ccb.qd.Do;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by han on 2015/7/9.
 */
public class 企业注册基本信息 {
    public 企业注册基本信息() {
        if (map==null)map=new HashMap<String, String>();
    }
    private Map<String,String>map;
    public String  get(String key){
        String v=map.get(key);
        return v;
    }
    public  企业注册基本信息  set(String key,String value){
        map.put(key,value);
        return this;
    }
}
