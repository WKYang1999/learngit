package com.example.demo.service;

import com.example.demo.domain.Role;

import java.util.List;

public interface RoleService {
    Role queryById(Long id);

    List<Role> getRoleList();

    void edit(Role role);

    void add(Role role);

    void deleteById(Long id);
}
