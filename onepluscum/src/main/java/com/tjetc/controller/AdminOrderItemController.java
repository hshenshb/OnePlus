package com.tjetc.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.tjetc.pojo.OrderItem;
import com.tjetc.service.AdminOrderItemService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/AdminOrderItemController")
public class AdminOrderItemController {
    @Reference
    private AdminOrderItemService adminOrderItemService;
    @RequestMapping("/getOrderitemById")
    public String getOrderitemById(HttpServletRequest request, HttpServletResponse response)  {
        String id = request.getParameter("id");
        List<OrderItem> list =adminOrderItemService.getOrderitemById(id);
        request.setAttribute("list", list);
        return "/administrator/order/orderItemList";

    }
}
