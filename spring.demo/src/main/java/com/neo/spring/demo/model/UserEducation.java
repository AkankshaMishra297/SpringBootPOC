package com.neo.spring.demo.model;

import java.util.Calendar;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class UserEducation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private float sscPercentage;

	private float hscPercentage;
	
	private float cgpa;

	private String sscBoardName;

	private String hscBoardName;

	private String universityName;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY
			)
	private User user;

	@Temporal(TemporalType.TIMESTAMP)
	private Calendar createdAt;

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public float getSscPercentage() {
		return sscPercentage;
	}

	public void setSscPercentage(float sscPercentage) {
		this.sscPercentage = sscPercentage;
	}

	public float getHscPercentage() {
		return hscPercentage;
	}

	public void setHscPercentage(float hscPercentage) {
		this.hscPercentage = hscPercentage;
	}

	public float getCgpa() {
		return cgpa;
	}

	public void setCgpa(float cgpa) {
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

//	public User getUser() {
//		return user;
//	}
//
	public void setUser(User user) {
		this.user = user;
	}

	public Calendar getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Calendar createdAt) {
		this.createdAt = createdAt;
	}

	
}
