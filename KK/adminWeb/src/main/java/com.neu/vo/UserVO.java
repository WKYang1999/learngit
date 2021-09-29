package com.neu.vo;

import com.neu.his.domain.BaseDomain;

public class UserVO extends BaseDomain {

    private String name;
    private String loginName;
    private String head;
    private String state;

    private int [] roleId;
    private String roleName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public int[] getRoleId() {
        return roleId;
    }

    public void setRoleId(int[] roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
