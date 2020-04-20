package com.tjetc.mapper;

import com.tjetc.pojo.OrderItem;

import java.util.List;

public interface OrderItemMapper {

    List<OrderItem> getByOrderId(String id);

    void add(OrderItem orderitem);
}
