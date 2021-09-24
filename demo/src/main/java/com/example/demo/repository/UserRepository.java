package com.example.demo.repository;

import com.example.demo.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {
    List<User> findByUserName(String userName);

    List<User> findByUserNameOrAge(String userName, int age);

    List<User> findByUserNameAndAge(String userName, int age);
}
