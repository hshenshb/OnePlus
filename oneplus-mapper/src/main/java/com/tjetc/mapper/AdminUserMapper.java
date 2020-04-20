package com.tjetc.mapper;

import com.tjetc.pojo.User;

import java.util.List;

public interface AdminUserMapper {

    List<User> getAll(String name);

    User validnameAdd(String username);

    void add(User user);

    User getById(int id);

    void update(User user);

    void delete(int id);

    User getByName(String username);

    User getByUName(String uname);
}
