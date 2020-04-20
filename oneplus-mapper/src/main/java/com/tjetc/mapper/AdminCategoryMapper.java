package com.tjetc.mapper;

import java.util.List;

import com.tjetc.pojo.Category;

public interface AdminCategoryMapper {

	Category validname(String name);

	List<Category> getAll(String name);

	void add(Category category);

	Category getById(int id);

	 

	void updatecategory(Category category);

	void delete(int id);

	Category getByName(String name);

    List<Category> getTrueAll();

	Category getByScid(int scid);
}
