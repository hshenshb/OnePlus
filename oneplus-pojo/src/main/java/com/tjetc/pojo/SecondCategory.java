package com.tjetc.pojo;

import java.io.Serializable;

public class SecondCategory implements Serializable {
	private static final long serialVersionUID = 8120940334768619010L;
	private int id;
	private String scname;
	private int cid;
	private String cname;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setScname(String scname) {
		this.scname = scname;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getScname() {
		return scname;
	}

	public int getCid() {
		return cid;
	}

	public String getCname() {
		return cname;
	}

	public SecondCategory(int id, String scname, int cid, String cname) {
		this.id = id;
		this.scname = scname;
		this.cid = cid;
		this.cname = cname;
	}

	public SecondCategory() {
	}

	public SecondCategory(int id, String scname, int cid) {
		this.id = id;
		this.scname = scname;
		this.cid = cid;
	}

	public SecondCategory(String scname, int cid) {
		this.scname = scname;
		this.cid = cid;
	}
}
