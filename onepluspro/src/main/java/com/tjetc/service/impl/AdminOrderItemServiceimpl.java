package com.tjetc.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.tjetc.mapper.AdminOrderItemMapper;
import com.tjetc.mapper.ProductMapper;
import com.tjetc.pojo.OrderItem;
import com.tjetc.service.AdminOrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
@Service
public class AdminOrderItemServiceimpl implements AdminOrderItemService {
    @Autowired
    private AdminOrderItemMapper adminOrderItemMapper;
    @Autowired
    private ProductMapper productMapper;
    @Override
    public List<OrderItem> getOrderitemById(String id) {
        List<OrderItem> list = adminOrderItemMapper.getOrderitemById(id);
        for (OrderItem orderitem : list) {
            orderitem.setP(productMapper.selectByPid(orderitem.getPid()));
        }
        return list;
    }
}
