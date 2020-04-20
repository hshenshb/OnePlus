package com.tjetc.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tjetc.mapper.AdminProductMapper;
import com.tjetc.pojo.Product;
import com.tjetc.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Service
public class ProductServiceimpl implements ProductService {
	@Autowired
	private AdminProductMapper d;




	@Override
	public Map<String, Object> list(String name, int page) {
		PageHelper.startPage(page,2);
		List<Product> list = d.list(name);
		PageInfo<Product> pageInfo=new PageInfo<Product>(list);
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("list",list);
		map.put("pageInfo",pageInfo);
		return map;
	}

	@Override
	public void add(Product product) {
	d.add(product);
	}

	@Override
	public Product getById(int id) {
		return d.getById(id);
	}

	@Override
	public void update(Product product) {
	d.update(product);
	}

	@Override
	public void delete(int id) {
	d.delete(id);
	}

	@Override
	public Product getById1(int parseInt) {
		return d.getById1(parseInt);
	}
}
