package com.tjetc.service.impl;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.dubbo.config.annotation.Service;
import com.tjetc.service.AdminCategoryService;
import org.springframework.beans.factory.annotation.Autowired;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tjetc.mapper.AdminCategoryMapper;
import com.tjetc.pojo.Category;
import org.springframework.stereotype.Component;

@Component
@Service
public class AdminCategoryServiceimpl implements AdminCategoryService {
	@Autowired
	private AdminCategoryMapper acMapper;
	@Override
	public Category validname(String name) {
		// TODO Auto-generated method stub
		return acMapper.validname(name);
	}
	@Override
	public Map<String, Object> getAll(String name, int page) {
		 PageHelper.startPage(page,5);
		 List<Category> list=acMapper.getAll(name);
		 PageInfo<Category> pageInfo=new PageInfo<>(list);
		 Map<String, Object> map=new HashMap<String, Object>();
		 map.put("list", list);
		 map.put("pageInfo", pageInfo);
		return map;
	}
	@Override
	public void add(Category category) {
		 acMapper.add(category);
		
	}
	@Override
	public Category getByName(String name) {
		// TODO Auto-generated method stub
		return acMapper.getByName(name);
	}
	@Override
	public Category getById(int id) {
		 
		return acMapper.getById(id);
	}

	@Override
	public Category getByScid(int id) {
		return acMapper.getByScid(id);
	}


	@Override
	public void delete(int id) {
		 
		acMapper.delete(id);
	}
	@Override
	public void updatecategory(Category category) {
		acMapper.updatecategory(category);
		
	}

	@Override
	public List<Category> getTrueAll() {
		return acMapper.getTrueAll();
	}


}
