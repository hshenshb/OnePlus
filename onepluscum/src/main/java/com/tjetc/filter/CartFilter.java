package com.tjetc.filter;


import com.tjetc.config.CookieUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@Component//过滤所有路径
@WebFilter(filterName = "CartFilter" ,urlPatterns = {"/cartController/*"})//过滤指定路径
public class CartFilter implements Filter {

    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request=(HttpServletRequest) servletRequest;
        HttpServletResponse response=(HttpServletResponse)servletResponse;
        String username= CookieUtils.getCookie(request,"username");
        if(username==null||username.equals("")){

            response.sendRedirect(request.getContextPath()+"/UserController/touserLogon1");
        return;
        }

       filterChain.doFilter(request,response);
    }

    public void destroy() {

    }
}
