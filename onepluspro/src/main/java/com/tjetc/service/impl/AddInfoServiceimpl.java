package com.tjetc.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.tjetc.mapper.AddInFoMapper;
import com.tjetc.pojo.AddrInfo;
import com.tjetc.service.AddInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Service
public class AddInfoServiceimpl implements AddInfoService {
    @Autowired
    private AddInFoMapper aMapper;

    public List<AddrInfo> select(int uid) {
        System.out.println("select方法");
        return aMapper.select(uid);
    }
    public AddrInfo selectMren(int uid) {
        // TODO Auto-generated method stub
        return aMapper.selectMren(uid);
    }
    public AddrInfo selectOne(int id) {
        // TODO Auto-generated method stub
        return aMapper.selectOne(id);
    }
    public void updateState(int id, int uid) {
        aMapper.updateState(id);
        Map<String, Object> map =new HashMap<String, Object>();
        map.put("id", id);
        map.put("uid",uid);
        aMapper.updateState1(map);

    }
    public void update(AddrInfo addrInfo) {
        aMapper.update(addrInfo);

    }
    public void delete(int id) {
        aMapper.delete(id);

    }
    public void add(AddrInfo addrInfo) {
        aMapper.add(addrInfo);

    }
}
