package com.ccb.qd.common;

import com.ccb.qd.Do.SearchRunMethods;
import org.springframework.core.annotation.AnnotationUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by han on 2015/7/10.
 */
public class WebStepUtils {


    public static String getAnnotation(Class cls, String type, String name){
        Annotation[] list = cls.getAnnotations();
        for (Annotation a :list){
            if(a.annotationType().getName().equals(type)) {
                Map<String, Object> map = AnnotationUtils.getAnnotationAttributes(a);
                return  map.get(name).toString();
            }
        }
        return null;
    }
    public static Map<String, Object> getAnnotation(Class cls, String type){
        Annotation[] list = cls.getAnnotations();
        for (Annotation a :list){
            if(a.annotationType().getName().equals(type)) {
                Map<String, Object> map = AnnotationUtils.getAnnotationAttributes(a);
                return  map;
            }
        }
        return null;
    }
    public static SearchRunMethods getMethodAnnotation(Class cls, String type){
        Map<String, Object> resultMap=new TreeMap<String, Object>();
        Method []ms = cls.getDeclaredMethods();
        for (Method m: ms ){
          Annotation[]as=   m.getDeclaredAnnotations();
            for(Annotation a:as){
                String name =a.annotationType().getName();
                if (name.indexOf(type)==name.length()-type.length()){//right find type in name
                    try {
                      Method value=   a.getClass().getDeclaredMethod("value");
                        String result= (String) value.invoke(a);
                        if (result==null) throw new RuntimeException("Annotation Value of Method  is null");
                        resultMap.put(result,m);
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
      return new SearchRunMethods(resultMap);

    }
    public static Annotation[] getAnnotation(Class cls){
        Annotation[] list = cls.getAnnotations();

        return list;
    }
    public static Class[]   getInterfaceArray(Object o){
       return o.getClass().getInterfaces();
    }

 }
