package com.neo.spring.demo.bean;

import java.io.Serializable;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserBean implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@JsonProperty("userName")
	@NotNull
	@Pattern(regexp ="([A-Za-z0-9]){4,12}", message = "invalid username")
	private String userName;
	
	@JsonProperty("userPassword")
	@NotNull
    //@Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,36}$", message = "Invalid Password")
	private String userPassword;
	
	@JsonProperty("userDetails")
	@Valid
	private UserDetailsBean userDetails;
	
	@JsonProperty("userEducation")
	@Valid
	private UserEducationBean userEducationBean;
	
	@JsonProperty("userEmployment")
	@Valid
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
