package com.tjetc.service;

import com.tjetc.pojo.User;


import java.util.List;
import java.util.Map;

public interface UsersService {
    void add(User users);

    List<User> getByUsername(String reg_account);

    List<User> list(User users);

    User getById(Long id);

    void delete(Long id);

    void update(User users);

    User validnameAdd(String username);

    void updatePsw(User users);

    void updatePhone(User users);

    void updateEmail(User users);

    void updateUname(User users);

    void updatePhoto(User users);

    void changeUserCode(String username, String codeBase64);

    void changeUserState(String username, Long i);


    Map<String,Object> listAll(User users, String curPage);
}
