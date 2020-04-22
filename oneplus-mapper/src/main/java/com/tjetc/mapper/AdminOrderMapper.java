package com.tjetc.mapper;

import com.tjetc.pojo.Orders;

import java.util.List;
import java.util.Map;

public interface AdminOrderMapper {


    List<Orders> list(String id);

    void send(String id);

    void send2(String oid);

    void send3(String oid);

    void send5(String oid);

    void delOrder(String oid);
}
