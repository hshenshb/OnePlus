package com.tjetc.config;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
public class CookieUtils {
    public static String getCookie(HttpServletRequest request,String name){
        Cookie[] cookies = request.getCookies();
        String value=null;
        if (cookies!=null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(name)) {
                    try {
                        value = URLDecoder.decode(cookie.getValue(), "UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return value;
    }
    public static void setCookie(HttpServletResponse response,String name,String json,int time){
        Cookie cookie = null;
        try {
            cookie = new Cookie(name, URLEncoder.encode(json,"utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        cookie.setMaxAge(time);
        cookie.setPath("/");
        response.addCookie(cookie);
    }

}
