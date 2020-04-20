package com.tjetc.service.impl;

import com.alibaba.dubbo.config.annotation.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tjetc.mapper.OrderMapper;
import com.tjetc.pojo.Orders;
import com.tjetc.pojo.Product;
import com.tjetc.service.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component
@Service
public class OrderServiceimpl implements OrderService {
    @Autowired
    OrderMapper orderMapper;
    @Override
    public Map<String, Object> list(int page, String oid, int uid) {
        Map<String, Object> mapin=new HashMap<String, Object>();
        mapin.put("oid",oid);
        mapin.put("uid",uid);

        PageHelper.startPage(page,2);
        List<Orders> list = orderMapper.list(mapin);
        PageInfo<Orders> pageInfo=new PageInfo<Orders>(list);
        Map<String, Object> map=new HashMap<String, Object>();
        map.put("list",list);
        map.put("pageInfo",pageInfo);
        return map;
    }

    @Override
    public void add(Orders order) {
        orderMapper.add(order);
    }
}
