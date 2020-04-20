package com.tjetc.service;

import com.tjetc.pojo.Admin;

import java.util.Map;

public interface AdminAdminService  {

    Admin validUsername(String username);

    Admin getById(int id);

    Map<String, Object> getAll(String name, int page);

    void addAdmin(Admin user);

    void updateAdmin(Admin user);

    void delete(int id);
}
