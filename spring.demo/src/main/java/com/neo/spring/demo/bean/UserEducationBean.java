package com.neo.spring.demo.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserEducationBean {
	
	@JsonProperty("sscPercentage")
	private String sscPercentage;

	@JsonProperty("hscPercentage")
	private String hscPercentage;
	
	@JsonProperty("cgpa")
	private String cgpa;

	@JsonProperty("sscBoardName")
	private String sscBoardName;

	@JsonProperty("hscBoardName")
	private String hscBoardName;

	@JsonProperty("universityName")
	private String universityName;

	public String getSscPercentage() {
		return sscPercentage;
	}

	public void setSscPercentage(String sscPercentage) {
		this.sscPercentage = sscPercentage;
	}

	public String getHscPercentage() {
		return hscPercentage;
	}

	public void setHscPercentage(String hscPercentage) {
		this.hscPercentage = hscPercentage;
	}

	public String getCgpa() {
		return cgpa;
	}

	public void setCgpa(String cgpa) {
		this.cgpa = cgpa;
	}

	public String getSscBoardName() {
		return sscBoardName;
	}

	public void setSscBoardName(String sscBoardName) {
		this.sscBoardName = sscBoardName;
	}

	public String getHscBoardName() {
		return hscBoardName;
	}

	public void setHscBoardName(String hscBoardName) {
		this.hscBoardName = hscBoardName;
	}

	public String getUniversityName() {
		return universityName;
	}

	public void setUniversityName(String universityName) {
		this.universityName = universityName;
	}

	@Override
	public String toString() {
		return "UserEducationBean [sscPercentage=" + sscPercentage + ", hscPercentage=" + hscPercentage + ", cgpa="
				+ cgpa + ", sscBoardName=" + sscBoardName + ", hscBoardName=" + hscBoardName + ", universityName="
				+ universityName + "]";
	}
	
	
	
	
}
