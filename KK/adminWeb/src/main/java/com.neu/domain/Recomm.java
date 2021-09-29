package com.neu.domain;

import com.neu.his.domain.BaseDomain;

public class Recomm extends BaseDomain {

	private Integer userId;
	private Integer videoId;
	private String date;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getVideoId() {
		return videoId;
	}

	public void setVideoId(Integer videoId) {
		this.videoId = videoId;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
}