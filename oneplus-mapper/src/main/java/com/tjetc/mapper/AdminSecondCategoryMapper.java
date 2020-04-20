package com.tjetc.mapper;

import com.tjetc.pojo.SecondCategory;

import java.util.List;
import java.util.Map;

public interface AdminSecondCategoryMapper {


    void add(SecondCategory secondCategory);

    SecondCategory getById(int id);

    void update(SecondCategory sCategory);

    void delete(int id);

    List<SecondCategory> getTrueall();

    SecondCategory validname(String name);

    SecondCategory getByName(String name);

    List<SecondCategory> list(String name);

    List<SecondCategory> getByCid(int id);
}
