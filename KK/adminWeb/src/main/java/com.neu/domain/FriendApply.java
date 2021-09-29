package com.neu.domain;

import com.neu.his.domain.BaseDomain;

public class FriendApply extends BaseDomain {

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