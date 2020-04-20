package com.tjetc.pojo;


import java.io.Serializable;

public class User implements Serializable {

	private static final long serialVersionUID = -1209807130842528739L;
	private int id;
	private String username;
	private String password;
	private String uname;
	private String email;
	private String phone;
	private String addr;
	private int state;
	private String code;
	private String photo;

	public User(String username, String password, String email, int state, String code, String photo) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.state = state;
		this.code = code;
		this.photo = photo;
	}

	public User(int id, String username, String password, String uname, String email, String phone, String addr,
			int state, String code, String photo) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.uname = uname;
		this.email = email;
		this.phone = phone;
		this.addr = addr;
		this.state = state;
		this.code = code;
		this.photo = photo;
	}

	public User(String username, String password, String uname, String email, String phone, String addr, int state,
			String code, String photo) {
		super();
		this.username = username;
		this.password = password;
		this.uname = uname;
		this.email = email;
		this.phone = phone;
		this.addr = addr;
		this.state = state;
		this.code = code;
		this.photo = photo;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public User(String username, String email, String code) {
		super();
		this.username = username;
		this.email = email;
		this.code = code;
	}

	public User(String username, String password, String email, int state, String code) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.state = state;
		this.code = code;
	}

	public User(String username, String password, String email, String code) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.code = code;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", uname=" + uname + ", email="
				+ email + ", phone=" + phone + ", addr=" + addr + ", state=" + state + ", code=" + code + ", photo="
				+ photo + "]";
	}

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

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public User(int id, String username, String password, String uname, String email, String phone, String addr,
			int state, String code) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.uname = uname;
		this.email = email;
		this.phone = phone;
		this.addr = addr;
		this.state = state;
		this.code = code;
	}

	public User() {
		super();
	}

	public User(String username, String password, String uname, String email, String phone, String addr, int state,
			String code) {
		super();
		this.username = username;
		this.password = password;
		this.uname = uname;
		this.email = email;
		this.phone = phone;
		this.addr = addr;
		this.state = state;
		this.code = code;
	}

	public User(int id, String username, String password, String uname, String email, String phone, String addr,
			int state) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.uname = uname;
		this.email = email;
		this.phone = phone;
		this.addr = addr;
		this.state = state;
	}

	public User(String username, String password, String uname, String email, String phone, String addr, int state) {
		super();
		this.username = username;
		this.password = password;
		this.uname = uname;
		this.email = email;
		this.phone = phone;
		this.addr = addr;
		this.state = state;
	}

	public User(String username, String password, String uname, String email, String phone, String addr) {
		super();
		this.username = username;
		this.password = password;
		this.uname = uname;
		this.email = email;
		this.phone = phone;
		this.addr = addr;
	}

	public User(int id, String username, String password, String uname, String email, String phone, String addr) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.uname = uname;
		this.email = email;
		this.phone = phone;
		this.addr = addr;
	}

}
