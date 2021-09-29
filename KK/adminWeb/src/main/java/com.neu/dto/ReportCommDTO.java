package com.neu.dto;

import com.neu.his.base.dto.BaseDTO;

public class ReportCommDTO extends BaseDTO {

    private Integer commId;
    private Integer userId;
    private String content;

    public Integer getCommId() {
        return commId;
    }

    public void setCommId(Integer commId) {
        this.commId = commId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
