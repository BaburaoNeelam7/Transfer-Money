package com.example.bank.response;

public class LoginResponse {
	
	private String status;
	private String statusText;
	private String id;
	private String accountNumberOne;
	private double accountOneBalance;
	private String accountNumberTwo;
	private double accountTwoBalance;
	private String firstName;
	private String lastName;
	private String userName;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getStatusText() {
		return statusText;
	}
	public void setStatusText(String statusText) {
		this.statusText = statusText;
	}
	public String getAccountNumberOne() {
		return accountNumberOne;
	}
	public void setAccountNumberOne(String accountNumberOne) {
		this.accountNumberOne = accountNumberOne;
	}
	public double getAccountOneBalance() {
		return accountOneBalance;
	}
	public void setAccountOneBalance(double accountOneBalance) {
		this.accountOneBalance = accountOneBalance;
	}
	public String getAccountNumberTwo() {
		return accountNumberTwo;
	}
	public void setAccountNumberTwo(String accountNumberTwo) {
		this.accountNumberTwo = accountNumberTwo;
	}
	public double getAccountTwoBalance() {
		return accountTwoBalance;
	}
	public void setAccountTwoBalance(double accountTwoBalance) {
		this.accountTwoBalance = accountTwoBalance;
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
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

}
