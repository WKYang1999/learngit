package com.neu.domain;

import com.neu.his.domain.BaseDomain;


public class Comments extends BaseDomain {

	private Integer videoId;
	private Integer userId;
	private String content;
	private String dateComm;

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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getDateComm() {
		return dateComm;
	}

	public void setDateComm(String dateComm) {
		this.dateComm = dateComm;
	}
}