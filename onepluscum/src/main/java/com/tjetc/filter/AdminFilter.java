package com.tjetc.filter;


import com.tjetc.pojo.Admin;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
//@Component//过滤所有路径
@WebFilter(filterName = "AdminFilter" ,urlPatterns = {"/adminController/touserLogon"})//过滤指定路径
public class AdminFilter implements Filter {

    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request=(HttpServletRequest) servletRequest;
        HttpServletResponse response=(HttpServletResponse)servletResponse;

       Admin admin= (Admin) request.getSession().getAttribute("admin");

        String uri = request.getRequestURI();
        System.out.println(uri);
       if(uri.indexOf("/main")!=-1){//判断来源路径是不是main
           if(admin==null){
               response.sendRedirect(request.getContextPath()+"/adminController/Login");
               return;
           }
       }

       filterChain.doFilter(request,response);
    }

    public void destroy() {

    }
}
