package com.tjetc.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.tjetc.pojo.User;
import com.tjetc.service.AdminUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/AdminUserController")
public class AdminUserController {
    @Reference
    private AdminUserService usService;
    @RequestMapping("/list")
    private String list(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("username")==null?"":request.getParameter("username");
        int page=Integer.parseInt(request.getParameter("page")==null?"1":request.getParameter("page"));


        Map<String, Object> map = usService.getAll(username, page);

        request.setAttribute("list", map.get("list"));
        request.setAttribute("username", username);
        request.setAttribute("pageInfo", map.get("pageInfo"));
        return "/administrator/User/userList";


    }
    @RequestMapping("/validnameAdd")
    private void validnameAdd(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");

        User user=usService.validnameAdd(username);

        response.getWriter().println(user==null);
    }
    @RequestMapping("/validUserUpdate")
    private void validUserUpdate(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String username = request.getParameter("username");
        int  id =Integer.parseInt( request.getParameter("id"));
        User user=usService.getByName(username);

        response.getWriter().println(user==null||user.getUsername().equals(usService.getById(id).getUsername()));

    }
    @RequestMapping("/add")
    private String add(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("username");

        String password =md5( request.getParameter("password"));
        String name = request.getParameter("uname");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String addr = request.getParameter("addr");

        User user=new User(username, password, name, email, phone, addr, 1);
        usService.add(user);
        return "redirect:/AdminUserController/list";

    }
    @RequestMapping("/update")
    private String update(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("username");
        String password =md5(request.getParameter("password"));
        String name = request.getParameter("uname");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String addr = request.getParameter("addr");
        int state = Integer.parseInt( request.getParameter("state"));
        int id = Integer.parseInt( request.getParameter("id"));

        User user=new User(id,username, password, name, email, phone, addr, state,null);
        usService.update(user);
        return "redirect:/AdminUserController/list";
    }
    @RequestMapping("/getById")
    private String getById(HttpServletRequest request, HttpServletResponse response) {
        int  id =Integer.parseInt( request.getParameter("upid"));



        User user=usService.getById(id);
        request.setAttribute("us", user);
        return "/administrator/User/userUpdate";


    }
    @RequestMapping("/delete")
    private String delete(HttpServletRequest request, HttpServletResponse response) throws IOException{
        int  id =Integer.parseInt( request.getParameter("deid"));
        usService.delete(id);
        return "redirect:/AdminUserController/list";
    }
    @RequestMapping("/toAdd")
    private String toAdd(HttpServletRequest request, HttpServletResponse response) {

        return "/administrator/User/userAdd";
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
