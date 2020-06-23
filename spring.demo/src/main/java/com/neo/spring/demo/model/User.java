package com.neo.spring.demo.model;



import lombok.*;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;

import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="user_id")
	private int id;

	@Column(unique = true)
	private String userName;

	private String password;

	private boolean active;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar createdAt;

	@OneToOne(mappedBy="user", cascade = CascadeType.ALL)
	private UserDetails userDetails;

	@OneToOne(mappedBy="user", cascade = CascadeType.ALL)
	private UserEducation userEducation;

	@OneToMany(mappedBy="user", cascade = CascadeType.ALL)
	private List<UserEmployment> userEmployment;
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY
			)
    @JoinTable(
        name = "user_role", 
        joinColumns = { @JoinColumn(name = "user_id") }, 
        inverseJoinColumns = { @JoinColumn(name = "role_id") }
    )
    Set<Role> role = new HashSet<>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Calendar getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Calendar createdAt) {
		this.createdAt = createdAt;
	}

	public UserDetails getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(UserDetails userDetails) {
		this.userDetails = userDetails;
	}

	public UserEducation getUserEducation() {
		return userEducation;
	}

	public void setUserEducation(UserEducation userEducation) {
		this.userEducation = userEducation;
	}

	public List<UserEmployment> getUserEmployment() {
		return userEmployment;
	}

	public void setUserEmployment(List<UserEmployment> userEmployment) {
		this.userEmployment = userEmployment;
	}

	public Set<Role> getRole() {
		return role;
	}

	public void setRole(Set<Role> role) {
		this.role = role;
	}

}

