package com.neu.domain;

import com.neu.his.domain.BaseDomain;

public class ReportComm extends BaseDomain {

	private Integer commId;

	private Integer userId;

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
}