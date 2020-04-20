package com.tjetc.mapper;

import java.util.List;

import com.tjetc.pojo.Admin;

public interface AdminAdminMapper {

	Admin validUsername(String username);

	int getCount(String name);

	List<Admin> getAll(String name);

	void addAdmin(Admin user);

	Admin getById(int id);

	void updateAdmin(Admin user);

	void delete(int id);
	
}
