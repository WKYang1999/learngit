package com.neu.domain;

import com.neu.his.domain.BaseDomain;

public class Content extends BaseDomain {

	private Integer yourId;
	private Integer userId;
	private String content;
	private String date;

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