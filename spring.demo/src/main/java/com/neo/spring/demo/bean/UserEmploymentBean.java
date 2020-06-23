package com.neo.spring.demo.bean;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserEmploymentBean {
	
	@JsonProperty("companyName")
	private String companyName;

	@JsonProperty("startDate")
	private Date startDate;
	
	@JsonProperty("endDate")
	private Date endDate;

	@JsonProperty("technology")
	private String technology;

	@JsonProperty("companyLocation")
	private String companyLocation;

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getTechnology() {
		return technology;
	}

	public void setTechnology(String technology) {
		this.technology = technology;
	}

	public String getCompanyLocation() {
		return companyLocation;
	}

	public void setCompanyLocation(String companyLocation) {
		this.companyLocation = companyLocation;
	}

	@Override
	public String toString() {
		return "UserEmploymentBean [companyName=" + companyName + ", startDate=" + startDate + ", endDate=" + endDate
				+ ", technology=" + technology + ", companyLocation=" + companyLocation + "]";
	}
	
	
	
	
}
