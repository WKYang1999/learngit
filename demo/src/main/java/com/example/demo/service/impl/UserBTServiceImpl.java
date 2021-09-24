package com.example.demo.service.impl;

import com.example.demo.domain.UserTB;
import com.example.demo.repository.UserTBRepository;
import com.example.demo.service.UserTBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserBTServiceImpl implements UserTBService {

    @Autowired
    private UserTBRepository userTBRepository;

    @Override
    public UserTB queryById(Long id) {
        return userTBRepository.findById(id).get();
    }

    @Override
    public List<UserTB> getUserTBList() {
        return userTBRepository.findAll();
    }

    @Override
    public void save(UserTB userTB) {
        userTBRepository.save(userTB);
    }

    @Override
    public void edit(UserTB userTB) {
        userTBRepository.save(userTB);
    }

    @Override
    public void deleteById(Long id) {
        userTBRepository.deleteById(id);
    }

    @Override
    public List<UserTB> findByNameOrUserSex(String name, String userSex) {
        return userTBRepository.findByNameOrUserSex(name,userSex);
    }

    @Override
    public List<UserTB> findByNameAndUserSex(String name, String userSex) {
        return userTBRepository.findByNameAndUserSex(name,userSex);
    }
}
