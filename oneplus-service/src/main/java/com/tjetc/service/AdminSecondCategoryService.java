package com.tjetc.service;

import com.tjetc.pojo.SecondCategory;

import java.util.List;
import java.util.Map;

public interface AdminSecondCategoryService {

     
    public void add(String name, int cid);
    public SecondCategory getById(int id);
    public void update(SecondCategory sCategory);
    public void delete(int id);
    public List<SecondCategory> getTrueall();
    public SecondCategory validname(String name);
    public SecondCategory getByName(String name);

    Map<String, Object> list(String name, int page);

    List<SecondCategory> getByCid(int id);
}
