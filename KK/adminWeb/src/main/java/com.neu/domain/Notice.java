package com.neu.domain;

import com.neu.his.domain.BaseDomain;


public class Notice extends BaseDomain {

	private String notice;
	private String title;
	private String time;

	public String getNotice() {
		return notice;
	}

	public void setNotice(String notice) {
		this.notice = notice;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
}
