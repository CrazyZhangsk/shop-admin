package com.fh.shop.backend.common;

import javax.servlet.http.HttpServletRequest;

public class WebContext {
    private static ThreadLocal<HttpServletRequest> threadLocal = new ThreadLocal<>();

    /*将当前请求与线程进行绑定 key为当前线程，省略不写*/
    public static void setRequest(HttpServletRequest request){
        threadLocal.set(request);
    }

    /*以当前线程作为key 获取value 省略不写*/
    public static HttpServletRequest getRequest(){
        return threadLocal.get();
    }

    public static  void remove(){
        threadLocal.remove();
    }
}
