package com.example.demo.service;

import com.example.demo.domain.User;

import java.util.List;
import java.util.Optional;


public interface UserService{

    List<User> getUserList();

    Optional<User> findUserById(Long id);

    void save(User user);

    void edit(User user);

    void deleteById(Long id);

    List<User> findByUserName(String userName);

    List<User> findByUserNameOrAge(String userName, int age);

    List<User> findByUserNameAndAge(String userName, int age);
}
