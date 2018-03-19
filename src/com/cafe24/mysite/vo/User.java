package com.cafe24.mysite.vo;

public class User {
	private long no;
	private String name;
	private String email;
	private String password;
	private String gender;
	private String regDate;
	
	public User() {
		// TODO Auto-generated constructor stub
	}

	public User(long no, String name) {
		super();
		this.no = no;
		this.name = name;
	}

	public User(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}
	
	public User(String name, String password, String gender) {
		super();
		this.name = name;
		this.password = password;
		this.gender = gender;
	}

	public User(String name, String email, String password, String gender) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.gender = gender;
	}
	
	public User(long no, String name, String email, String gender) {
		super();
		this.no = no;
		this.name = name;
		this.email = email;
		this.gender = gender;
	}

	public User(long no, String name, String email, String password, String gender) {
		super();
		this.no = no;
		this.name = name;
		this.email = email;
		this.password = password;
		this.gender = gender;
	}

	public long getNo() {
		return no;
	}

	public void setNo(long no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	@Override
	public String toString() {
		return "User [no=" + no + ", name=" + name + ", email=" + email + ", password=" + password + ", gender="
				+ gender + ", regDate=" + regDate + "]";
	}
	
}
