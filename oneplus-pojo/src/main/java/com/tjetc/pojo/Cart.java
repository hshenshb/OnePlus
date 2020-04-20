package com.tjetc.pojo;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Cart implements Serializable {
	private static final long serialVersionUID = 6394759867628401986L;
	private Map<Integer, CartItem> map = new HashMap<Integer, CartItem>();
	private double total;

	@Override
	public String toString() {
		return "Cart [map=" + map + ", total=" + total + "]";
	}



	public double getTotal() {
		return total;
	}
	
	 

	public void setTotal(double total) {
		this.total = total;
	}

	// 以后把购物车项显示到jsp
	public Collection<CartItem> getCartItems() {
		return map.values();
	}

	public void addCart(CartItem cartItem) {
		// 从购物车中查找
		CartItem item = map.get(cartItem.getProduct().getId());
		// 原来购物车中有，对数量相加
		if (item != null) {
			// 数量相加后给原来购物车项
			item.setCount(item.getCount() + cartItem.getCount());
		} else {
			// 购物车中没有
			map.put(cartItem.getProduct().getId(), cartItem);
		}
		this.setTotal(this.getTotal() + cartItem.getSubTotal());
	}

	// 删除单项购物车商品
	public void delete(int id) {
		CartItem cartItem = map.remove(id);
		this.setTotal(this.getTotal() - cartItem.getSubTotal());
	}

	// 清空购物车
	public void deleteAll() {
		map.clear();
		this.setTotal(0);
	}

	public void update(CartItem cartItem, String mm) {
		// 从购物车中查找
		CartItem item = map.get(cartItem.getProduct().getId());
		// 原来购物车中有，对数量相加
		if (item != null) {
			// 数量相加后给原来购物车项
			item.setCount(cartItem.getCount());
		} else {
			// 购物车中没有
			map.put(cartItem.getProduct().getId(), cartItem);
		}
		if (mm.equals("jia")) {
			this.setTotal(this.getTotal() + cartItem.getProduct().getPrice());
		} else if (mm.equals("jian")) {
			Collection<CartItem> cartItems = getCartItems();
			double total = 0;
			for (CartItem cari : cartItems) {
				total += cari.getSubTotal();
			}
			this.setTotal(total);
		}
	}
}