package com.tjetc.pojo;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Orders implements Serializable {
	private static final long serialVersionUID = 3091350919120123716L;
	private String id;
	private String oname;
	private String phone;
	private String addr;
	private int usid;
	private double total;
	private String ordertime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
	private int state;
	private List<OrderItem> orderItems = new ArrayList<OrderItem>();

	public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	public Orders(String id, String oname, String phone, String addr, int usid, double total, String ordertime,
			int state) {
		super();
		this.id = id;
		this.oname = oname;
		this.phone = phone;
		this.addr = addr;
		this.usid = usid;
		this.total = total;
		this.ordertime = ordertime;
		this.state = state;
	}

	public Orders() {
		super();
	}

	public Orders(String oname, String phone, String addr, int usid, double total, String ordertime, int state) {
		super();
		this.oname = oname;
		this.phone = phone;
		this.addr = addr;
		this.usid = usid;
		this.total = total;
		this.ordertime = ordertime;
		this.state = state;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOname() {
		return oname;
	}

	public void setOname(String oname) {
		this.oname = oname;
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

	public int getUsid() {
		return usid;
	}

	public void setUsid(int usid) {
		this.usid = usid;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public String getOrdertime() {
		return ordertime;
	}

	public void setOrdertime(String ordertime) {
		this.ordertime = ordertime;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "Orders [id=" + id + ", oname=" + oname + ", phone=" + phone + ", addr=" + addr + ", usid=" + usid
				+ ", total=" + total + ", ordertime=" + ordertime + ", state=" + state + ", orderItems=" + orderItems
				+ "]";
	}

	

}
