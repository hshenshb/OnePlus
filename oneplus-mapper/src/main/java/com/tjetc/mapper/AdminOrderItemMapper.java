package com.tjetc.mapper;

import com.tjetc.pojo.OrderItem;

import java.util.List;

public interface AdminOrderItemMapper {
    List<OrderItem> getOrderitemById(String id);
}
