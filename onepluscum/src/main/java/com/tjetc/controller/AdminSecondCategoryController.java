package com.tjetc.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.tjetc.pojo.Category;
import com.tjetc.pojo.SecondCategory;
import com.tjetc.service.AdminCategoryService;
import com.tjetc.service.AdminSecondCategoryService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/AdminSecondCategoryController")
public class AdminSecondCategoryController {
    @Reference
    AdminCategoryService cService;
    @Reference
    AdminSecondCategoryService aSecondCservice;
    @RequestMapping("/validnameUpdate")
    private void validnameUpdate(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("scname");
        int  id =Integer.parseInt( request.getParameter("id"));
        SecondCategory category=aSecondCservice.getByName(name);

        response.getWriter().println(category==null||category.getScname().equals(aSecondCservice.getById(id).getScname()));
    }
    @RequestMapping("/validnameAdd")
    private void validnameAdd(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String  name = request.getParameter("name");

        SecondCategory sCategory=aSecondCservice.validname(name);

        response.getWriter().println(sCategory==null);
    }
    @RequestMapping("/list")
    public String list(HttpServletRequest request, HttpServletResponse response)  {
        String name=request.getParameter("scname")==null?"":request.getParameter("scname");
        int page=Integer.parseInt(request.getParameter("page")==null?"1":request.getParameter("page"));
        int pageSize=5;



        Map<String,Object> map =aSecondCservice.list(name,page);
        request.setAttribute("list", map.get("list"));
        request.setAttribute("name", name);
        request.setAttribute("pageInfo",  map.get("pageInfo"));
        return "/administrator/secondCategory/sCategoryList";

    }
    @RequestMapping("/toadd")
    public String toAdd(HttpServletRequest request){

        List<Category> list = cService.getTrueAll();
        request.setAttribute("list", list);
        return "/administrator/secondCategory/sCategoryAdd";
    }
    @RequestMapping("/add")
    public String add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name=request.getParameter("name");
        int cid=Integer.parseInt(request.getParameter("cid"));
        SecondCategory sCategory=new SecondCategory(name, cid);
        aSecondCservice.add(name,cid);
        return "redirect:/AdminSecondCategoryController/list";
    }
    @RequestMapping("/getById")
    public String getById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int id=Integer.parseInt(request.getParameter("id"));
        SecondCategory scCategory= aSecondCservice.getById(id);
        List<Category> list = cService.getTrueAll();
        request.setAttribute("list", list);
        request.setAttribute("sCategory", scCategory);
         return "/administrator/secondCategory/sCategoryUpdate";
    }
    @RequestMapping("/update")
    public String update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int id=Integer.parseInt(request.getParameter("id"));
        int cid=Integer.parseInt(request.getParameter("cid"));
        String name=request.getParameter("scname");
        SecondCategory sCategory=new SecondCategory(id, name, cid);
        System.out.println(sCategory);
        aSecondCservice.update(sCategory);
        return "redirect:/AdminSecondCategoryController/list";


    }
    @RequestMapping("/delete")
    public String delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int id=Integer.parseInt(request.getParameter("id"));

        aSecondCservice.delete(id);
        return "redirect:/AdminSecondCategoryController/list";

    }

}
