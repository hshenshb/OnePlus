package com.tjetc.service;

import com.tjetc.pojo.OrderItem;

import java.util.List;

public interface AdminOrderItemService {
    public List<OrderItem> getOrderitemById(String id);

}
