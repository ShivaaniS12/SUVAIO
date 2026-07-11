package com.th.model;

import java.sql.Timestamp;

public class User {
	
	private int userID;
	private String username;
	private String password;
	private String email;
	private String address;
	private String role;
	private Timestamp createdDate ;
	private Timestamp lastLoginDate ;
	private String status;
	private String mobile;
	
	
	public User() {
		// TODO Auto-generated constructor stub
	}
	
	public User(int userID,  String username, String password, String email, String address, String role,
			Timestamp createdDate, Timestamp lastLoginDate) {
		super();
		this.userID = userID;

		this.username = username;
		this.password = password;
		this.email = email;
		this.address = address;
		this.role = role;
		this.createdDate = createdDate;
		this.lastLoginDate = lastLoginDate;
	}

	
	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Timestamp getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public Timestamp getLastLoginDate() {
		return lastLoginDate;
	}

	public void setLastLoginDate(Timestamp lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}
	
	@Override
	public String toString() {
		return "User [userID=" + userID 
				+ ", username=" + username 
				+ ", password=" + password 
				+ ", email=" + email 
				+ ", address=" + address 
				+ ", role=" + role 
				+ ", createdDate=" + createdDate 
				+ ", lastLoginDate=" + lastLoginDate 
				+ "]";
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	
	public String getMobile() {
	    return mobile;
	}

	public void setMobile(String mobile) {
	    this.mobile = mobile;
	}
}
