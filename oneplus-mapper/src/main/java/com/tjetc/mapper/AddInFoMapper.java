package com.tjetc.mapper;

import com.tjetc.pojo.AddrInfo;
import com.tjetc.pojo.User;

import java.util.List;
import java.util.Map;

public interface AddInFoMapper {

    AddrInfo selectMren(int id);

    List<AddrInfo> select(int uid);

    AddrInfo selectOne(int id);

    void updateState(int id);

    void updateState1(Map<String, Object> map);

    void update(AddrInfo addrInfo);

    void delete(int id);

    void add(AddrInfo addrInfo);

    void add1(User user);
}
