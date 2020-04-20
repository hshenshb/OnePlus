package com.tjetc.service;

import com.tjetc.pojo.User;

import java.util.List;
import java.util.Map;

public interface AdminUserService {


    public Map<String,Object> getAll(String name, int page);

    public User validnameAdd(String username);

    public void add(User user );

    public  User getById(int id) ;

    public void update(User user);

    public void delete(int id);

    public User getByName(String username);
}
