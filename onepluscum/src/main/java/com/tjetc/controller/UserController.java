package com.tjetc.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.tjetc.config.CookieUtils;
import com.tjetc.pojo.Admin;
import com.tjetc.pojo.Cart;
import com.tjetc.pojo.User;
import com.tjetc.service.AdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

@Controller
@RequestMapping("/UserController")
public class UserController {
    @Reference
    private AdminUserService usService;
    @Autowired
    private RedisTemplate ObjectRedisTemplate;
    @RequestMapping("/touserLogon")
    private String touserLogin(){
        return "/user/userLogin";
    }
    @RequestMapping("/touserLogon1")
    private String touserLogin1(){
        return "/user/userLogin";
    }
    @RequestMapping("/toZhuCe")
    private String toZhuCe(){
        return "/user/userAdd";
    }
    @RequestMapping("/add")
    private String add(HttpServletRequest request, HttpServletResponse response){
        String username = request.getParameter("username");

        String password =md5( request.getParameter("password"));
        String name = request.getParameter("uname");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String addr = request.getParameter("addr");

        User user=new User(username, password, name, email, phone, "北京市-北京城区-东城区-"+username, 1);
        usService.add(user);
        return "/user/userLogin";
    }
    @RequestMapping("/validUsername")
    private void validUsername(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("login_account");

        User user=usService.validnameAdd(username);
        System.out.println(user);

        response.getWriter().println(user!=null);

    }
    @RequestMapping("/validPassword")
    private void validPassword(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("login_account");
        String password = request.getParameter("login_pass");

        User user=usService.validnameAdd(username);

        response.getWriter().println(user != null && user.getPassword().equals(md5(password)));



    }
    @RequestMapping("/Login")
    private String Login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("login_account");
        if(username==null||username.equals("")){
            username= CookieUtils.getCookie(request,"username");
        }else {
            CookieUtils.setCookie(response,"username",username,24*60*60);
        }
        ValueOperations forValue = ObjectRedisTemplate.opsForValue();
       forValue.set("username",username);


        String cuid="cart"+ UUID.randomUUID().toString().replaceAll("-", "");
        Cookie c=new Cookie("cuid",cuid);
        c.setMaxAge(24*60*60);
        c.setPath("/");
        forValue.set(c.getValue(),new Cart());
        response.addCookie(c);
        return "redirect:/goodspage/list";



    }
    @RequestMapping("/zhuxiao")
    private String zhuxiao(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Cookie c=new Cookie("username","");
        c.setMaxAge(0);
        c.setPath("/");
        response.addCookie(c);
        ValueOperations forValue = ObjectRedisTemplate.opsForValue();
       forValue.set("username","");


        return "redirect:/UserController/touserLogon";



    }

    public String md5(String str)  {
        // 生成一个MD5计算摘要
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        // digest()最后确定返回md5 hash值，返回值为8位字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
        byte[] b = md.digest(str.getBytes());
        // BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
        String string = new BigInteger(1, b).toString(16);
        return string;
    }
}
