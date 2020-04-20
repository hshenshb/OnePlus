package com.tjetc.service;

import java.util.List;
import java.util.Map;


import com.tjetc.pojo.Product;


public interface ProductService {




	public Map<String,Object> list(String name, int page);

	public void add(Product product);

	public Product getById(int id);

	public void update(Product product);

	public void delete(int id);

    Product getById1(int parseInt);
}
