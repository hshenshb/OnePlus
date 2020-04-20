package com.tjetc.pojo;

import java.io.Serializable;

public class Admin  implements Serializable {
	private static final long serialVersionUID = 4017995315335865873L;
	private int id;
	private String username;
	private String password;
	private String email;
	private int power;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public int getPower() {
		return power;
	}
	public void setPower(int power) {
		this.power = power;
	}

	public Admin(int id, String username, String password, String email, int power) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.power = power;
	}
	public Admin(String username, String password, String email, int power) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.power = power;
	}
	public Admin() {
		super();
	}
	@Override
	public String toString() {
		return "Admin [id=" + id + ", username=" + username + ", password=" + password + ", email=" + email + ", power="
				+ power + "]";
	}
	
	 

}
