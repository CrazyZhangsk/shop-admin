package com.fh.shop.utils;

import org.springframework.cglib.beans.BeanMap;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class TransFormUtils {

    //Bean转Map
    public Map bean2Map(Class bean){
        Map<String, String> resultMap = new HashMap<>();
        if (bean != null) {
            BeanMap beanMap = BeanMap.create(bean);
            for (Object key : beanMap.keySet()) {
                if (String.valueOf(beanMap.get(key))!="null"){
                    resultMap.put(key + "", String.valueOf(beanMap.get(key)));
                }
            }
        }
        return resultMap;
    }

}
