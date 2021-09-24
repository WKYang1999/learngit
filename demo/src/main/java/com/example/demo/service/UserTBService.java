package com.example.demo.service;

import com.example.demo.domain.UserTB;

import java.util.List;

public interface UserTBService {
    UserTB queryById(Long id);

    List<UserTB> getUserTBList();

    void save(UserTB userTB);

    void edit(UserTB userTB);

    void deleteById(Long id);

    List<UserTB> findByNameOrUserSex(String name, String userSex);

    List<UserTB> findByNameAndUserSex(String name, String userSex);
}
