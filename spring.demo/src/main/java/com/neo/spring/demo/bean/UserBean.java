package com.neo.spring.demo.bean;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserBean {
	
	@JsonProperty("userName")
	private String userName;
	
	@JsonProperty("userPassword")
	private String userPassword;
	
	@JsonProperty("userDetails")
	private UserDetailsBean userDetails;
	
	@JsonProperty("userEducation")
	private UserEducationBean userEducationBean;
	
	@JsonProperty("userEmployment")
	private  List<UserEmploymentBean> userEmploymentBean;

	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public UserDetailsBean getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(UserDetailsBean userDetails) {
		this.userDetails = userDetails;
	}
	
	
	public UserEducationBean getUserEducationBean() {
		return userEducationBean;
	}

	public void setUserEducationBean(UserEducationBean userEducationBean) {
		this.userEducationBean = userEducationBean;
	}

	public List<UserEmploymentBean> getUserEmploymentBean() {
		return userEmploymentBean;
	}

	public void setUserEmploymentBean(List<UserEmploymentBean> userEmploymentBean) {
		this.userEmploymentBean = userEmploymentBean;
	}
	
	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	@Override
	public String toString() {
		return "UserBean [userName=" + userName + ", userPassword=" + userPassword + ", userDetails=" + userDetails
				+ ", userEducationBean=" + userEducationBean + ", userEmploymentBean=" + userEmploymentBean + "]";
	}

	
	
	

}
