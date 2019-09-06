package com.pojo;

import java.util.List;

public class User {
	private String emailId;
	private String firstName;
	private String lastName;
	private String password;
	List<Holding> holdings;
	
	public User() {
		this.emailId = "";
		this.firstName = "";
		this.lastName = "";
		this.password = "";
	}
	
	public User(String emailId, String firstName, String lastName, String password, List<Holding> holdings) {
		super();
		this.emailId = emailId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.holdings = holdings;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<Holding> getHoldings() {
		return holdings;
	}
	public void setHoldings(List<Holding> holdings) {
		this.holdings = holdings;
	}
	
}
