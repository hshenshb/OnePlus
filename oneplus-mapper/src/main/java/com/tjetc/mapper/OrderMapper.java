package com.tjetc.mapper;

import com.tjetc.pojo.Orders;

import java.util.List;
import java.util.Map;

public interface OrderMapper {

    List<Orders> list(Map<String, Object> mapin);

    void add(Orders order);
}
