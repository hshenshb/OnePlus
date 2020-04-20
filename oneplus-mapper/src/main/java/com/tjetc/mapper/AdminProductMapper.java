package com.tjetc.mapper;

import com.tjetc.pojo.Product;

import java.util.List;

public interface AdminProductMapper {


    List<Product> list(String name);

    void add(Product product);

    Product getById(int id);

    void update(Product product);

    void delete(int id);

    Product getById1(int parseInt);
}
