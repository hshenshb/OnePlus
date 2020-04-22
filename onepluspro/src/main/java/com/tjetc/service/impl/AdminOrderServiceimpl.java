package com.tjetc.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tjetc.mapper.AdminOrderMapper;
import com.tjetc.pojo.Orders;
import com.tjetc.service.AdminOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Component
@Service
public class AdminOrderServiceimpl implements AdminOrderService {
    @Autowired
    private AdminOrderMapper adminOrderMapper;


    @Override
    public Map<String, Object> list(String id, int page) {
        PageHelper.startPage(page,2);
        List<Orders> list = adminOrderMapper.list(id);
        PageInfo<Orders> pageInfo=new PageInfo<Orders>(list);
        Map<String,Object> mapout= new HashMap<String, Object>();
        mapout.put("list",list);
        mapout.put("pageInfo",pageInfo);
        return mapout;
    }

    @Override
    public void send(String id) {
        adminOrderMapper.send(id);
    }

    @Override
    public void send2(String oid) {

            adminOrderMapper.send2(oid);

    }

    @Override
    public void send3(String oid) {
        adminOrderMapper.send3(oid);
    }

    @Override
    public void send5(String oid) {
        adminOrderMapper.send5(oid);
    }

    @Override
    public void delOrder(String oid) {

        adminOrderMapper.delOrder(oid);
    }
}
