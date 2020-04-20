package com.tjetc.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.tjetc.mapper.OrderItemMapper;
import com.tjetc.pojo.OrderItem;
import com.tjetc.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Service
public class OrderItemServiceImpl implements OrderItemService {
    @Autowired
    OrderItemMapper orderItemMapper;
    @Override
    public List<OrderItem> getByOrderId(String id) {
        return orderItemMapper.getByOrderId(id);
    }

    @Override
    public void addOrderItems(List<OrderItem> orderitems) {
        for (OrderItem orderitem : orderitems) {
            orderItemMapper.add(orderitem);
        }
    }
}
