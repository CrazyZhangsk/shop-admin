package com.fh.shop.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookUtil {

    public static void writeCookie(HttpServletResponse response,String doMain, String name, String val, int flag){
        Cookie cookie = new Cookie(name,val);
        cookie.setPath("/");
        cookie.setDomain(doMain);
        if (flag>=0){
            cookie.setMaxAge(flag);
        }
        response.addCookie(cookie);
    }

    public static String readCookie(HttpServletRequest request,String name){
        Cookie[] cookies = request.getCookies();
        if (cookies==null){
            return "";
        }
        for (int i = 0; i < cookies.length; i++) {
            String s = cookies[i].getName();
            if (s.equals(name)){
                String value = cookies[i].getValue();
                return value;
            }
        }
        return "";
    }

    public static void delCookie(String name,HttpServletResponse response,String domain){
        writeCookie(response, domain, name, "",0);
    }
}
