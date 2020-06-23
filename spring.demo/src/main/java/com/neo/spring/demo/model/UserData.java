package com.neo.spring.demo.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class UserData {
	
	private User u;

	public User getU() {
		return u;
	}

	public void setU(User u) {
		this.u = u;
	}

}
