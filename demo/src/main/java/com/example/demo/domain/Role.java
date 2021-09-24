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
@Table(name = "roletb")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roleId;
    @Column(nullable = false,unique = true,length = 32)
    private String rolePost;
    @Column(nullable = false,length = 32)
    private int rolePay;
    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    //@Cascade(value = {CascadeType.SAVE_UPDATE})
    @JoinTable(name = "user_role_tb",
        joinColumns = {@JoinColumn(name = "user_role_tb_role_id")},
        inverseJoinColumns = {@JoinColumn(name = "user_role_tb_user_id")})
    @NotFound(action = NotFoundAction.IGNORE)
    private Set<UserTB> userSet = new HashSet<>();

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRolePost() {
        return rolePost;
    }

    public void setRolePost(String rolePost) {
        this.rolePost = rolePost;
    }

    public int getRolePay() {
        return rolePay;
    }

    public void setRolePay(int rolePay) {
        this.rolePay = rolePay;
    }

    public Set<UserTB> getUserSet() {
        return userSet;
    }

    public void setUserSet(Set<UserTB> userSet) {
        this.userSet = userSet;
    }

    @Override
    public String toString() {
        //return "Role[" + "roleId=" + roleId + ", rolePost=" + rolePost + ", rolePay=" + rolePay + "]";
        return "Role[" + "roleId=" + roleId + ", rolePost=" + rolePost + ", rolePay=" + rolePay + ",userSet=" + userSet + "]";
    }

    public String showRoleAndUserSet(){
        return "Role[" + "roleId=" + roleId + ", rolePost=" + rolePost + ", rolePay=" + rolePay + ",userSet=" + userSet + "]";
    }
}
