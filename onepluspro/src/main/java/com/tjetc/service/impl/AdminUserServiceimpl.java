package com.tjetc.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tjetc.mapper.AddInFoMapper;
import com.tjetc.mapper.AdminUserMapper;
import com.tjetc.pojo.User;
import com.tjetc.service.AdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Service
public class AdminUserServiceimpl implements AdminUserService {
    @Autowired
    private AdminUserMapper aUmapper;
    @Autowired
    private AddInFoMapper addInFoMapper;

    @Override
    public Map<String,Object> getAll(String name, int page) {
        PageHelper.startPage(page,2);
        List<User> list=aUmapper.getAll(name);
        PageInfo<User> pageInfo=new PageInfo<User>(list);
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("list",list);
        map.put("pageInfo",pageInfo);
        return map;
    }

    public User validnameAdd(String username) {
        return  aUmapper.validnameAdd(username);
    }

    public void add(User user ) {
        // TODO Auto-generated method stub
        aUmapper.add(user);
        user=aUmapper.getByUName(user.getUsername());
        System.out.println(user);
        addInFoMapper.add1(user);
    }

    public  User getById(int id) {
        return aUmapper.getById(id);
    }

    public void update(User user) {
        aUmapper.update(user);

    }

    public void delete(int id) {
        aUmapper.delete(id);

    }

    public User getByName(String username) {
        return aUmapper.getByName(username);
    }
}
