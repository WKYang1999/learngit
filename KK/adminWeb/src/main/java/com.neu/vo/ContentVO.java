package com.neu.vo;

import com.neu.his.domain.BaseDomain;

public class ContentVO extends BaseDomain {

    private Integer ssId;
    private Integer yourId;
    private Integer userId;
    private String name;
    private String content;
    private String date;

    public Integer getSsId() {
        return ssId;
    }

    public void setSsId(Integer ssId) {
        this.ssId = ssId;
    }

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
