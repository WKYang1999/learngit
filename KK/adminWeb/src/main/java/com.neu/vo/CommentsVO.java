package com.neu.vo;

import com.neu.his.domain.BaseDomain;


public class CommentsVO extends BaseDomain {

	private String videoName;
	private String name;
	private Integer videoId;
	private Integer userId;
	private String content;
	private String dateComm;

	public String getVideoName() {
		return videoName;
	}

	public void setVideoName(String videoName) {
		this.videoName = videoName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

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
