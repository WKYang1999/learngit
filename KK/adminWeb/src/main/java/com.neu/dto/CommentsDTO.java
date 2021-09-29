package com.neu.dto;

import com.neu.his.base.dto.BaseDTO;


public class CommentsDTO extends BaseDTO {

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
