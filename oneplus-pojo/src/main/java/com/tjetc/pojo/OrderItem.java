package com.tjetc.pojo;



import java.io.Serializable;

public class OrderItem implements Serializable {
	private static final long serialVersionUID = -9201244022917518584L;
	private int id;
	private int counts;
	private double subtotal;
	private int pid;
	private String orderid;
	private Product p;


	public OrderItem(int counts, double subtotal, int pid, String orderid, Product p) {
		super();
		this.counts = counts;
		this.subtotal = subtotal;
		this.pid = pid;
		this.orderid = orderid;
		this.p = p;
	}

	public OrderItem(int counts, int pid, String orderid) {
		super();
		this.counts = counts;
		this.pid = pid;
		this.orderid = orderid;

		this.subtotal=this.p.getPrice()*counts;
	}

	public Product getP() {
		return p;
	}

	public void setP(Product p) {
		this.p = p;
	}

	public OrderItem(int id, int counts, double subtotal, int pid, String orderid) {
		super();
		this.id = id;
		this.counts = counts;
		this.subtotal = subtotal;
		this.pid = pid;
		this.orderid = orderid;
	}

	public OrderItem() {
		super();
	}

	public OrderItem(int counts, double subtotal, int pid, String orderid) {
		super();
		this.counts = counts;
		this.subtotal = subtotal;
		this.pid = pid;
		this.orderid = orderid;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCounts() {
		return counts;
	}

	public void setCounts(int counts) {
		this.counts = counts;
	}

	public double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	@Override
	public String toString() {
		return "OrderItem [id=" + id + ", count=" + counts + ", subtotal=" + subtotal + ", pid=" + pid + ", orderid="
				+ orderid + ", p=" + p + "]";
	}
	
	public OrderItem(int id, String orderid, int pid, int counts, double subtotle) {
		super();
		this.id = id;
		this.orderid = orderid;
		this.pid = pid;
		this.counts = counts;
		this.subtotal = subtotle;
	}
	public OrderItem(String orderid, int pid, int counts, double subtotle) {
		super();
		this.orderid = orderid;
		this.pid = pid;
		this.counts = counts;
		this.subtotal = subtotle;
	}
	 

}
