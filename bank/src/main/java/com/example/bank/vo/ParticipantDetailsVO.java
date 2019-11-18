package com.example.bank.vo;

public class ParticipantDetailsVO {
	
	private String id;
	private String firstName;
	private String lastName;
	private String userName;
	private String accountNumberOne;
	private String accountNumberTwo;
	private String accountOneBalance;
	private String accountTwoBalance;
	
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
	public String getAccountNumberOne() {
		return accountNumberOne;
	}
	public void setAccountNumberOne(String accountNumberOne) {
		this.accountNumberOne = accountNumberOne;
	}
	public String getAccountNumberTwo() {
		return accountNumberTwo;
	}
	public void setAccountNumberTwo(String accountNumberTwo) {
		this.accountNumberTwo = accountNumberTwo;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAccountOneBalance() {
		return accountOneBalance;
	}
	public void setAccountOneBalance(String accountOneBalance) {
		this.accountOneBalance = accountOneBalance;
	}
	public String getAccountTwoBalance() {
		return accountTwoBalance;
	}
	public void setAccountTwoBalance(String accountTwoBalance) {
		this.accountTwoBalance = accountTwoBalance;
	}

}
