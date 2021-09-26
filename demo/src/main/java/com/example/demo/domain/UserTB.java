package com.example.demo.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "usertb")
public class UserTB {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    @Column(nullable = false,unique = true,length = 32)
    private String name;
    @Column(nullable = false,length = 32)
    private String password;
    @Column(nullable = false,length = 32)
    private String userSex;
    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    //@Cascade(value = {CascadeType.SAVE_UPDATE})
    @JoinTable(name = "user_role_tb",
        joinColumns = {@JoinColumn(name = "user_role_tb_user_id")},
        inverseJoinColumns = {@JoinColumn(name = "user_role_tb_role_id")})
    @NotFound(action = NotFoundAction.IGNORE)
    private Set<Role> roleSet = new HashSet<>();

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoleSet() {
        return roleSet;
    }

    public void setRoleSet(Set<Role> roleSet) {
        this.roleSet = roleSet;
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    @Override
    public String toString() {
        return "UserTB{" + "userId=" + userId + ", name=" + name + ", userSex=" + userSex + "}";
    }

    public String showUserAndRoleSet(){
        return "UserTB{" + "userId=" + userId + ", name=" + name + ", userSex=" + userSex + ",roleSet=" + roleSet + "}";
    }
}
