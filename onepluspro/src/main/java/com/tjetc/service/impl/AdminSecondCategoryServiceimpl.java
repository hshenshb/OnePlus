package com.tjetc.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tjetc.mapper.AdminSecondCategoryMapper;
import com.tjetc.pojo.SecondCategory;
import com.tjetc.service.AdminSecondCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Component
@Service
public class AdminSecondCategoryServiceimpl implements AdminSecondCategoryService {
    @Autowired
    private AdminSecondCategoryMapper scMapper;

    @Override
    public void add(String name, int cid) {
        SecondCategory secondCategory=new SecondCategory(name,cid);
        scMapper.add(secondCategory);
    }

    @Override
    public SecondCategory getById(int id) {
        return scMapper.getById(id);
    }

    @Override
    public void update(SecondCategory sCategory) {
        scMapper.update(sCategory);
    }

    @Override
    public void delete(int id) {
        scMapper.delete(id);
    }

    @Override
    public List<SecondCategory> getTrueall() {
        return scMapper.getTrueall();
    }

    @Override
    public SecondCategory validname(String name) {
        return scMapper.validname(name);
    }

    @Override
    public SecondCategory getByName(String name) {
        return scMapper.getByName(name);
    }

    @Override
    public Map<String, Object> list(String name, int page) {

        PageHelper.startPage(page,2);
        List<SecondCategory> list = scMapper.list(name);
        PageInfo<SecondCategory> pageInfo=new PageInfo<SecondCategory>(list);
        Map<String,Object> mapout= new HashMap<String, Object>();
        mapout.put("list",list);
        mapout.put("pageInfo",pageInfo);

        return mapout;
    }

    @Override
    public List<SecondCategory> getByCid(int id) {
        return scMapper.getByCid(id);
    }
}
