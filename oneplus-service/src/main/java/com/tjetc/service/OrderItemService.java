package com.tjetc.service;



import com.tjetc.pojo.OrderItem;

import java.util.List;

public interface OrderItemService {


    List<OrderItem> getByOrderId(String id);

    void addOrderItems(List<OrderItem> orderitems);
}
