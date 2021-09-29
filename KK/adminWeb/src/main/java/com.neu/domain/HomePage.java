package com.neu.domain;

import com.neu.his.domain.BaseDomain;

public class HomePage extends BaseDomain {

	private String name;
	private String state;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
}