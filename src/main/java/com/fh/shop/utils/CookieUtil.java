package com.fh.shop.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtil {

    public static String readCookie(HttpServletRequest request, String key) {
        Cookie[] cookies = request.getCookies();
        if (cookies==null){
            return "";
        }
        for (Cookie cookie : cookies) {
            if (key.equals(cookie.getName())) {
                return cookie.getValue();
            }
        }
        return "";
    }

    public static void writeCookie(HttpServletResponse response, String key,
                                   String val, String domain, int maxAge){
        Cookie cookie = new Cookie(key, val);
        cookie.setDomain(domain);
        cookie.setPath("/");
        if (maxAge > -1){
            cookie.setMaxAge(maxAge);
        }
        response.addCookie(cookie);
    }

    public static void delCookie(HttpServletResponse response, String key, String domain){
        writeCookie(response, key, "", domain, 0);
    }
}
