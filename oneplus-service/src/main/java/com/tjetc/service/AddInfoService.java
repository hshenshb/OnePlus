package com.tjetc.service;

import com.tjetc.pojo.AddrInfo;

import java.util.List;

public interface AddInfoService {
    public List<AddrInfo> select(int uid);
    AddrInfo selectMren(int id);
    public AddrInfo selectOne(int id) ;
    public void updateState(int id, int uid);
    public void update(AddrInfo addrInfo);
    public void delete(int id) ;
    public void add(AddrInfo addrInfo);
}
