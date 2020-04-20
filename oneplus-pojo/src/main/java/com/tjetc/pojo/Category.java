package com.tjetc.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Category implements Serializable {
	private static final long serialVersionUID = 4044141915848650405L;
	private int id;
	private String cname;
	private String photo;
	private List<SecondCategory> secondcategories = new ArrayList<SecondCategory>();
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public List<SecondCategory> getSecondcategories() {
		return secondcategories;
	}
	public void setSecondcategories(List<SecondCategory> secondcategories) {
		this.secondcategories = secondcategories;
	}
	public Category(int id, String cname, String photo, List<SecondCategory> secondcategories) {
		super();
		this.id = id;
		this.cname = cname;
		this.photo = photo;
		this.secondcategories = secondcategories;
	}
	public Category(String cname, String photo) {
		super();
		this.cname = cname;
		this.photo = photo;
	}
	public Category(int id, String cname, String photo) {
		super();
		this.id = id;
		this.cname = cname;
		this.photo = photo;
	}
	public Category() {
		super();
	}
	

}
