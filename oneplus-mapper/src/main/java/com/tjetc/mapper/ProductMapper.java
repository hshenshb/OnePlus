package com.tjetc.mapper;

import com.tjetc.pojo.Product;

import java.util.List;

public interface ProductMapper {
    Product selectByPid(int pid);

    List<Product> getByName(String name);

    List<Product> getByCid(int cid);

    List<Product> getByScid(int scid);
}
