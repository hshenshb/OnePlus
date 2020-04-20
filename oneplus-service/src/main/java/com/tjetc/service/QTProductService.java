package com.tjetc.service;

import com.tjetc.pojo.Category;
import com.tjetc.pojo.Product;

import java.util.List;
import java.util.Map;

public interface QTProductService {
    public Product getProductById(int pid);

    public Map<String,Object> likeList(String name,int page);

    public List<Product> list();

    public List<Product> listByCid(int cid);

    public List<Product> listBySCid(int scid);


}