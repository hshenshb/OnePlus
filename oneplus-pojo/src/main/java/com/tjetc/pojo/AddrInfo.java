package com.tjetc.pojo;

import java.io.Serializable;

public class AddrInfo implements Serializable {
	private static final long serialVersionUID = 3956733255009332474L;
	private int id;
	private String aname;
	private String phone;
	private String addr;
	private int usid;
	private int state;
	public AddrInfo(int id, String aname, String phone, String addr, int usid, int state) {
		super();
		this.id = id;
		this.aname = aname;
		this.phone = phone;
		this.addr = addr;
		this.usid = usid;
		this.state = state;
	}
	public AddrInfo() {
		super();
	}
	public AddrInfo(int id, String aname, String phone, String addr) {
		super();
		this.id = id;
		this.aname = aname;
		this.phone = phone;
		this.addr = addr;
	}
	public AddrInfo(String aname, String phone, String addr, int usid, int state) {
		super();
		this.aname = aname;
		this.phone = phone;
		this.addr = addr;
		this.usid = usid;
		this.state = state;
	}
	public int getId() {
		return id;
	}
	public String getAname() {
		return aname;
	}
	public String getPhone() {
		return phone;
	}
	public String getAddr() {
		return addr;
	}
	public int getUsid() {
		return usid;
	}
	public int getState() {
		return state;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setAname(String aname) {
		this.aname = aname;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public void setUsid(int usid) {
		this.usid = usid;
	}
	public void setState(int state) {
		this.state = state;
	}
	@Override
	public String toString() {
		return "AddrInfo [id=" + id + ", aname=" + aname + ", phone=" + phone + ", addr=" + addr + ", usid=" + usid
				+ ", state=" + state + "]";
	}






}
