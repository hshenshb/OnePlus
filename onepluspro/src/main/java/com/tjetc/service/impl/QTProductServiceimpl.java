package com.tjetc.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tjetc.mapper.AdminCategoryMapper;
import com.tjetc.mapper.AdminSecondCategoryMapper;
import com.tjetc.mapper.ProductMapper;
import com.tjetc.pojo.Category;
import com.tjetc.pojo.Product;
import com.tjetc.service.QTProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Service
public class QTProductServiceimpl implements QTProductService {
    @Autowired
    private ProductMapper productMapper;


    public Product getProductById(int pid) {
        // TODO Auto-generated method stub
        return productMapper.selectByPid(pid);
    }

    @Override
    public Map<String, Object> likeList(String name, int page) {
        PageHelper.startPage(page,3);
        List<Product> list =productMapper.getByName(name);

        PageInfo<Product> pageInfo=new PageInfo<Product>(list);
        Map<String, Object> map=new HashMap<String,Object>();
        map.put("list",list);
        map.put("pageInfo",pageInfo);
        return map;
    }



    public List<Product> list() {
        // TODO Auto-generated method stub
        return productMapper.getByName("");
    }

    public List<Product> listByCid(int cid) {
        List<Product> list=productMapper.getByCid(cid);
        return list;

    }

    public List<Product> listBySCid(int scid) {
        List<Product> list=productMapper.getByScid(scid);
        return list;
    }





}
