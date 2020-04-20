package com.tjetc.service;

import com.tjetc.pojo.Category;

import java.util.List;
import java.util.Map;

public interface AdminCategoryService {

    public Category validname(String name);

    public Map<String, Object> getAll(String name, int page);

    public void add(Category category);

    public Category getByName(String name);

    public Category getById(int id);
    public Category getByScid(int id);


    public void delete(int id);

    public void updatecategory(Category category);

    List<Category> getTrueAll();
}
