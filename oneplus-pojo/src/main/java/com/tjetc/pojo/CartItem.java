package com.tjetc.pojo;

import java.io.Serializable;

public class CartItem implements Serializable {
	private static final long serialVersionUID = 4834330336546226533L;
	private Product product;
	private int count;
	private double subTotal;
	
	@Override
	public String toString() {
		return "CartItem [product=" + product + ", count=" + count + ", subTotal=" + subTotal + "]";
	}
	public CartItem(Product product, int count, double subTotal) {
		super();
		this.product = product;
		this.count = count;
		this.subTotal = subTotal;
	}
	public CartItem() {
		super();
	}
	public Product getProduct() {
		return product;
	}
	public int getCount() {
		return count;
	}
	public double getSubTotal() {
		return this.count*this.product.getPrice();
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public void setCount(int count) {
		this.count = count;
	}
}
