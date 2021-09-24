package com.example.demo.repository;

import com.example.demo.domain.UserTB;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserTBRepository extends JpaRepository<UserTB,Long> {

    List<UserTB> findByNameOrUserSex(String name, String userSex);

    List<UserTB> findByNameAndUserSex(String name, String userSex);
}
