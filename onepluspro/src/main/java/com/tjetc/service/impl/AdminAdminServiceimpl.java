package com.tjetc.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tjetc.mapper.AdminAdminMapper;
import com.tjetc.pojo.Admin;
import com.tjetc.service.AdminAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Service
public class AdminAdminServiceimpl implements AdminAdminService {
   @Autowired
    private AdminAdminMapper aMapper;
    @Override
    public Admin validUsername(String username) {
        // TODO Auto-generated method stub
        return aMapper.validUsername(username);
    }

    public int getCount(String name) {
        // TODO Auto-generated method stub
        return aMapper.getCount(name);
    }

    public Map<String, Object> getAll(String name,int page) {
        PageHelper.startPage(page,5);
        List<Admin> list = aMapper.getAll( name);
        PageInfo<Admin> pageInfo=new PageInfo<Admin>(list);
        Map<String, Object> map=new HashMap<String, Object>();
        map.put("list", list);
        map.put("pageInfo", pageInfo);
        return map;
    }





    public void addAdmin(Admin user) {
        aMapper.addAdmin(user);

    }

    public Admin getById(int id) {
        // TODO Auto-generated method stub
        return aMapper.getById(  id);
    }

    public void updateAdmin(Admin user) {
        // TODO Auto-generated method stub
        aMapper.updateAdmin(  user);
    }

    public void delete(int id) {
        // TODO Auto-generated method stub
        aMapper.delete(  id);
    }


}
