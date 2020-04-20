package com.tjetc.service;

import com.tjetc.pojo.OrderItem;
import com.tjetc.pojo.Orders;
import com.tjetc.pojo.User;

import java.util.List;
import java.util.Map;

public interface OrderService {


    Map<String, Object> list(int page, String oid, int uii);

    void add(Orders order);
}

