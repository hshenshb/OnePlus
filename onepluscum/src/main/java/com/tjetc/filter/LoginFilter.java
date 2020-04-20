package com.tjetc.filter;

import com.tjetc.config.CookieUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "LoginFilter" ,urlPatterns = {"/UserController/touserLogon"})//过滤指定路径
public class LoginFilter implements Filter {
    @Autowired
    private RedisTemplate ObjectRedisTemplate;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request=(HttpServletRequest) servletRequest;
        HttpServletResponse response=(HttpServletResponse)servletResponse;
        String username= CookieUtils.getCookie(request,"username");
        String cuid = CookieUtils.getCookie(request, "cuid");
        if(username==null||username.equals("")){
            if(cuid!=null&&!cuid.equals("")){
                ValueOperations forValue = ObjectRedisTemplate.opsForValue();


                forValue.set(cuid,"");
            }

            Cookie c=new Cookie("cuid",cuid);
            c.setMaxAge(0);
            c.setPath("/");
            response.addCookie(c);
            response.sendRedirect(request.getContextPath()+"/UserController/touserLogon1");

        }else {

            response.sendRedirect(request.getContextPath()+"/UserController/Login");
        }
    }

    @Override
    public void destroy() {

    }
}
