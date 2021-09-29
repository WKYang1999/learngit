package com.neu.dto;

import com.neu.his.base.dto.BaseDTO;


public class FriendApplyDTO extends BaseDTO {

    private Integer yourId;
    private Integer userId;

    public Integer getYourId() {
        return yourId;
    }

    public void setYourId(Integer yourId) {
        this.yourId = yourId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
