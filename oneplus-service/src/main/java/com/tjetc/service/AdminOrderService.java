package com.tjetc.service;


import java.util.Map;

public interface AdminOrderService {


    public Map<String,Object> list(String id, int page);

    public void send(String id);

    void send2(String oid);

    void send3(String oid);

    void send5(String oid);
}
