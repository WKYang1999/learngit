package com.neu.vo;

import com.neu.his.domain.BaseDomain;


public class FriendApplyVO extends BaseDomain {

    private Integer yourId;
    private Integer userId;
    private String userName;

    public Integer getYourId() {
        return yourId;
    }

    public void setYourId(Integer yourId) {
        this.yourId = yourId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
