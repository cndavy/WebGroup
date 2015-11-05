package com.ccb.qd.Do;

import java.util.Iterator;
import java.util.Map;

public class SearchRunMethods {
    private final Map<String, Object> value;
    private String current;
    public SearchRunMethods(Map<String, Object> value) {
        this.value = value;
        current= (String) value.keySet().toArray() [0];
    }

    public Map<String, Object> getValue() {
        return value;
    }
    public Object Current(){
        return  value.get(current);
    }
    public Object Name(String name){
        return  value.get(name);
    }
    public int getLength(){
        return value.size();
    }
    public Object Next (){
        Iterator<String> iterator = value.keySet().iterator();
        while (iterator.hasNext()) {
            Object key = iterator.next();
            if (key==current){
                if(iterator.hasNext()){
                    current=iterator.next();
                   return value.get(current);
                }else {
                    return null;
                }
            }

        }
     return null;
    }
}
