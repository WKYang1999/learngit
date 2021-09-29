package com.neu.dto;

import com.neu.his.base.dto.BaseDTO;


public class LikeDTO extends BaseDTO {

    private Integer videoId;
    private Integer userId;

    public Integer getVideoId() {
        return videoId;
    }

    public void setVideoId(Integer videoId) {
        this.videoId = videoId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
