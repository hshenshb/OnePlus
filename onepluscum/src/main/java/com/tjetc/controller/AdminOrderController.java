package com.tjetc.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.tjetc.pojo.Orders;
import com.tjetc.service.AdminOrderService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Map;

@Controller
@RequestMapping("/AdminOrderController")
public class AdminOrderController {
    @Reference
    private AdminOrderService adminOrderService;
    @RequestMapping("/list")
    public String list(HttpServletRequest request, HttpServletResponse response) {
        int page = request.getParameter("page")==null?1:Integer.parseInt(request.getParameter("page"));

        String id = request.getParameter("id")==null?"":request.getParameter("id");

        Map<String,Object> map=adminOrderService.list(id,page);
        request.setAttribute("list", map.get("list"));
        request.setAttribute("id", id);
        request.setAttribute("pageInfo", map.get("pageInfo"));
        return "/administrator/order/orderList";
     }
    @RequestMapping("/send")
    public String send(HttpServletRequest request, HttpServletResponse response)  {
        String id = request.getParameter("id");
        adminOrderService.send(id);
        return "redirect:/AdminOrderController/list";

    }

}
