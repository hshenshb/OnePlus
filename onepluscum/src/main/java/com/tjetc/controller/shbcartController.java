package com.tjetc.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import com.tjetc.pojo.Cart;
import com.tjetc.pojo.CartItem;
import com.tjetc.pojo.Product;

import com.tjetc.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonObjectDeserializer;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.HttpCookie;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/cartController")
public class shbcartController {
	private static final String Cart = null;

	 @Reference
	 private ProductService productService;

	@Autowired
	private RedisTemplate ObjectRedisTemplate;


	@RequestMapping("/tocartnull")
	protected String  tocartnull()
	{

		return "user/cart/cart-null";

	}
	@RequestMapping("/tocart")
	protected String  tocart()
	{

		return "user/cart/cart";

	}
	@RequestMapping("/toHtml")
	protected String  toHtml(HttpServletRequest request,HttpServletResponse response)
	{
		Cart cart=getCart(request,response);
		System.out.println("*******************###############"+cart);
	 	request.setAttribute("cart",cart);
		String url=null;
		if(cart==null||cart.getTotal()==0) {
			url="user/cart/cart-null";
		}else {
			url="user/cart/cart";
		}

		return url;

	}
	//***********************这个整合需要改动selectByPid()
	// 向购物车添加购物车项
	@RequestMapping("/add")
	protected String  add(String count,String id,HttpServletRequest request,HttpServletResponse response){

		// 创建购物项
		CartItem cartItem = new CartItem();
		if (count == null) {
			cartItem.setCount(1);
		} else {
			cartItem.setCount(Integer.parseInt(count));
		}
		// 获取点击的商品
		Product product = productService.getById(Integer.parseInt(id));
		cartItem.setProduct(product);

		// 从session中得到购物车项判断是否存在
		Cart cart = getCart(request,response);

		// 原来有把cartItem对象加到购物车中
		cart.addCart(cartItem);
		cartItem.setCount(Integer.parseInt(count));

		 update(cart,request,response);
		cart = getCart(request,response);

		// 重定向到购物车页面
		return "redirect:/cartController/toHtml";

	}

	private Cart getCart(HttpServletRequest request,HttpServletResponse response) {
		Cookie[] cookies = request.getCookies();
		Cart cart=new Cart();
		boolean b=false;
		String decode="";
		for (Cookie cookie : cookies) {
			decode=cookie.getName();

			if (decode.equals("cuid")&&!cookie.getValue().equals("")){
				ValueOperations forValue = ObjectRedisTemplate.opsForValue();

				Object o = (Object)forValue.get(cookie.getValue());
				String s=JSON.toJSONString(o);
				//将value中的值装换成一个object对象
				JSONObject object = JSON.parseObject(s);
				//得到value的key名为cartitems的json对象
				JSONArray jsonArray = object.getJSONArray("cartItems");
				//得到value的key名为total的值
				Double total = object.getDouble("total");
				//将cartitem的json对象转换为cartitems列表
				List<CartItem> cartItems = JSON.parseArray(jsonArray.toJSONString(), CartItem.class);
				for (CartItem cartItem : cartItems) {
					cart.addCart(cartItem);
				}
				//将total的json对象
				cart.setTotal(total);
				b=true;
			}

		}
		if (!b){
			ValueOperations forValue = ObjectRedisTemplate.opsForValue();
			String cuid="cart"+ UUID.randomUUID().toString().replaceAll("-", "");
			Cookie c=new Cookie("cuid",cuid);
			c.setMaxAge(24*60*60);
			c.setPath("/");
			forValue.set(c.getValue(),cart);
			response.addCookie(c);
		}
		return cart;
	}
	//
	protected Cart update(Cart cart,HttpServletRequest request,HttpServletResponse response ){
		Cookie[] cookies = request.getCookies();

		String decode="";

		for (Cookie cookie : cookies) {
			System.out.println(cookie.getName()+"存rides");
			decode=cookie.getName();
			if (decode.equals("cuid")&&!cookie.getValue().equals("")){
				ValueOperations forValue = ObjectRedisTemplate.opsForValue();


					forValue.set(cookie.getValue(),cart);


			}
			else {
				System.out.println("else");
				ValueOperations forValue = ObjectRedisTemplate.opsForValue();
				String cuid="cart"+ UUID.randomUUID().toString().replaceAll("-", "");
				Cookie c=new Cookie("cuid",cuid);
				c.setMaxAge(24*60*60);
				c.setPath("/");
				forValue.set(c.getValue(),cart);
				response.addCookie(c);
			}
		}
		return cart;
	}
	@RequestMapping("/update")
	protected void update(HttpServletRequest request, HttpServletResponse response)
			throws  IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		int count = Integer.parseInt(request.getParameter("count"));
		String mm = request.getParameter("mm");
		CartItem cartItem = new CartItem();
		cartItem.setCount(count);
		// 获取点击的商品
		Product product = productService.getById(id);
		cartItem.setProduct(product);
		// 从session中得到购物车项判断是否存在
		Cart cart = getCart(request,response);
		// 原来有把cartItem对象加到购物车中
		cart.update(cartItem, mm);
		//更新购物车cookie
		update(cart,request,response);

		response.getWriter().print(cart.getTotal());
	}
	@RequestMapping("/delete")
	protected void delete(HttpServletRequest request, HttpServletResponse response)
			throws  IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Cart cart = getCart(request,response);
		cart.delete(id);
		//更新购物车cookie
		update(cart,request,response);
		response.getWriter().print(true);
	}
	@RequestMapping("/deleteAll")
	protected String  deleteAll(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		Cart cart = getCart(request,response);
		cart.deleteAll();
		//更新购物车cookie
		update(cart,request,response);
		return  "user/cart/cart-null";

	}

	@RequestMapping("/validCount")
	@ResponseBody
	private int validCount(String pid,HttpServletRequest request,HttpServletResponse response) {

		int pid3=Integer.parseInt(pid);
		int count = 0;

		Cart cart=new Cart();
		//判断cookie中是否有cookie
		Cookie[] cookies = request.getCookies();
		if(cookies==null){
			String cuid="cart"+ UUID.randomUUID().toString().replaceAll("-", "");
			Cookie c=new Cookie("cuid",cuid);
			c.setMaxAge(24*60*60);
			c.setPath("/");
			response.addCookie(c);


		}

		boolean b=false;
		String ckname="";
		for (Cookie cookie : cookies) {
			ckname=cookie.getName();
			if(ckname.equals("cuid")&&!cookie.getValue().equals("")){
				ValueOperations forValue = ObjectRedisTemplate.opsForValue();
				Object o = (Object)forValue.get(cookie.getValue());
				String s=JSON.toJSONString(o);
				//将value中的值装换成一个object对象
				JSONObject object = JSON.parseObject(s);
				//得到value的key名为cartitems的json对象
				JSONArray jsonArray = object.getJSONArray("cartItems");
				//得到value的key名为total的值
				Double total = object.getDouble("total");
				//将cartitem的json对象转换为cartitems列表
				List<CartItem> cartItems = JSON.parseArray(jsonArray.toJSONString(), CartItem.class);
				for (CartItem cartItem : cartItems) {
					cart.addCart(cartItem);
				}
				//将total的json对象
				cart.setTotal(total);

				if(cart==null){
					forValue.set(cookie.getValue(),cart);
				}
				b=true;
			}
		}
		if (!b){
			ValueOperations forValue = ObjectRedisTemplate.opsForValue();
			String cuid="cart"+ UUID.randomUUID().toString().replaceAll("-", "");
			Cookie c=new Cookie("cuid",cuid);
			c.setMaxAge(24*60*60);
			c.setPath("/");
			forValue.set(c.getValue(),cart);
			response.addCookie(c);
		}
			if(cart!=null){
			Collection<CartItem> cartItems = cart.getCartItems();
			for (CartItem cartItem : cartItems) {
				// 获取购物车中的Pid
				int pid2 = cartItem.getProduct().getId();
				if (pid2 == pid3) {
					// 获取数量
					count = cartItem.getCount();
				}
			}}


		return count;
	}

}
/*
* package com.tjetc.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import com.tjetc.pojo.Cart;
import com.tjetc.pojo.CartItem;
import com.tjetc.pojo.Product;

import com.tjetc.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonObjectDeserializer;
import org.springframework.http.HttpCookie;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/cartController")
public class shbcartController {
	private static final String Cart = null;

	 @Reference
	 private ProductService productService;



	@RequestMapping("/tocartnull")
	protected String  tocartnull()
	{

		return "user/cart/cart-null";

	}
	@RequestMapping("/tocart")
	protected String  tocart()
	{

		return "user/cart/cart";

	}
	@RequestMapping("/toHtml")
	protected String  toHtml(HttpServletRequest request)
	{
		Cookie[] cookies = request.getCookies();
		Cart cart=new Cart();
		boolean b=false;
		String decode="";
		for (Cookie cookie : cookies) {
			try {
				decode = URLDecoder.decode(cookie.getName(), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			if (decode.equals("cart")&&!cookie.getValue().equals("")){
				String value="";
				b=true;
				try {
					value=URLDecoder.decode(cookie.getValue(),"UTF-8");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				JSONObject object = JSON.parseObject(value);
				JSONArray jsonArray = object.getJSONArray("cartItems");
				Double total = object.getDouble("total");
				List<CartItem> cartItems = JSON.parseArray(jsonArray.toJSONString(), CartItem.class);

				for (CartItem cartItem : cartItems) {
					cart.addCart(cartItem);
					cartItem.setProduct(productService.getById(cartItem.getProduct().getId()));


				}
				cart.setTotal(total);

			}
		}
	 	request.setAttribute("cart",cart);
		String url=null;
		if(cart==null||cart.getTotal()==0) {
			url="user/cart/cart-null";
		}else {
			url="user/cart/cart";
		}

		return url;

	}
	//***********************这个整合需要改动selectByPid()
	// 向购物车添加购物车项
	@RequestMapping("/add")
	protected String  add(String count,String id,HttpServletRequest request,HttpServletResponse response){
		System.out.println("====="+count);
		// 创建购物项
		CartItem cartItem = new CartItem();
		if (count == null) {
			cartItem.setCount(1);
		} else {
			cartItem.setCount(Integer.parseInt(count));
		}
		// 获取点击的商品
		Product product = productService.getById1(Integer.parseInt(id));
		cartItem.setProduct(product);
		// 从session中得到购物车项判断是否存在
		Cart cart = getCart(request,response);
		// 原来有把cartItem对象加到购物车中
		cart.addCart(cartItem);
		cartItem.setCount(Integer.parseInt(count));
		update(cart,request,response);

		// 重定向到购物车页面
		return "redirect:/cartController/toHtml";

	}

	private Cart getCart(HttpServletRequest request,HttpServletResponse response) {
		Cookie[] cookies = request.getCookies();
		Cart cart=new Cart();
		boolean b=false;
		String decode="";
		for (Cookie cookie : cookies) {
			try {
				decode = URLDecoder.decode(cookie.getName(), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			if (decode.equals("cart")&&!cookie.getValue().equals("")){
				String value="";
				b=true;
				try {
					value=URLDecoder.decode(cookie.getValue(),"UTF-8");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				JSONObject object = JSON.parseObject(value);
				JSONArray jsonArray = object.getJSONArray("cartItems");
				Double total = object.getDouble("total");
				List<CartItem> cartItems = JSON.parseArray(jsonArray.toJSONString(), CartItem.class);
				for (CartItem cartItem : cartItems) {

					cart.addCart(cartItem);

				}
				cart.setTotal(total);
			}
		}
		if (!b){
			cart=new Cart();
			String s = JSON.toJSONString(cart);
			String s1="";
			try {
				s1 = URLEncoder.encode(s, "utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			Cookie c=new Cookie("cart",s1);
			c.setMaxAge(24*60*60);
			response.addCookie(c);
		}
		return cart;
	}
	//
	private Cart update(Cart cart,HttpServletRequest request,HttpServletResponse response ){

		String s = JSON.toJSONString(cart);
		String s1="";
		try {
			s1 = URLEncoder.encode(s, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		Cookie c=new Cookie("cart",s1);
		c.setMaxAge(24*60*60);
		response.addCookie(c);
		return cart;
	}
	@RequestMapping("/update")
	protected void update(HttpServletRequest request, HttpServletResponse response)
			throws  IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		int count = Integer.parseInt(request.getParameter("count"));
		String mm = request.getParameter("mm");
		CartItem cartItem = new CartItem();
		cartItem.setCount(count);
		// 获取点击的商品
		Product product = productService.getById(id);
		cartItem.setProduct(product);
		// 从session中得到购物车项判断是否存在
		Cart cart = getCart(request,response);
		// 原来有把cartItem对象加到购物车中
		cart.update(cartItem, mm);
		//更新购物车cookie
		update(cart,request,response);

		response.getWriter().print(cart.getTotal());
	}
	@RequestMapping("/delete")
	protected void delete(HttpServletRequest request, HttpServletResponse response)
			throws  IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Cart cart = getCart(request,response);
		cart.delete(id);
		//更新购物车cookie
		update(cart,request,response);
		response.getWriter().print(true);
	}
	@RequestMapping("/deleteAll")
	protected String  deleteAll(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		Cart cart = getCart(request,response);
		cart.deleteAll();
		//更新购物车cookie
		update(cart,request,response);
		return  "user/cart/cart-null";

	}

	@RequestMapping("/validCount")
	@ResponseBody
	private int validCount(String pid,HttpServletRequest request,HttpServletResponse response) {
		System.out.println("运行验证");
		int pid3=Integer.parseInt(pid);
		int count = 0;
		System.out.println("pid" + pid);
		Cart cart=new Cart();
		//判断cookie中是否有cookie
		Cookie[] cookies = request.getCookies();
		if(cookies==null){
			cart=new Cart();
			String s = JSON.toJSONString(cart);
			String s1="";
			try {
				s1 = URLEncoder.encode(s, "utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			Cookie c=new Cookie("cart",s1);
			c.setMaxAge(24*60*60);
			response.addCookie(c);
		}

		boolean b=false;
		String decode="";
		for (Cookie cookie : cookies) {
			try {
				//得到cookie的key
				decode = URLDecoder.decode(cookie.getName(), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			//cookie的名称为cart并且内容不为空
			if (decode.equals("cart")&&!cookie.getValue().equals("")){
				String value="";
				b=true;
				try {
					//得到cookie中的value
					value=URLDecoder.decode(cookie.getValue(),"UTF-8");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				//将value中的值装换成一个object对象
				JSONObject object = JSON.parseObject(value);
				//得到value的key名为cartitems的json对象
				JSONArray jsonArray = object.getJSONArray("cartItems");
				//得到value的key名为total的值
				Double total = object.getDouble("total");
				//将cartitem的json对象转换为cartitems列表
				List<CartItem> cartItems = JSON.parseArray(jsonArray.toJSONString(), CartItem.class);
				for (CartItem cartItem : cartItems) {
					cart.addCart(cartItem);
				}
				//将total的json对象
				cart.setTotal(total);
			}
		}
		if (!b){
			cart=new Cart();
			String s = JSON.toJSONString(cart);
			String s1="";
			try {
				s1 = URLEncoder.encode(s, "utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			Cookie c=new Cookie("cart",s1);
			c.setMaxAge(24*60*60);
			response.addCookie(c);
		}

			Collection<CartItem> cartItems = cart.getCartItems();
			for (CartItem cartItem : cartItems) {
				// 获取购物车中的Pid
				int pid2 = cartItem.getProduct().getId();
				if (pid2 == pid3) {
					// 获取数量
					count = cartItem.getCount();
				}
			}

		System.out.println("count=="+count);
		return count;
	}

}
* */