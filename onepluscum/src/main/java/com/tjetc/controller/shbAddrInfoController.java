package com.tjetc.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.dubbo.config.annotation.Reference;
import com.tjetc.config.CookieUtils;
import com.tjetc.service.AddInfoService;
import com.tjetc.service.AdminAdminService;
import com.tjetc.service.AdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tjetc.pojo.AddrInfo;
import com.tjetc.pojo.User;

@Controller
@RequestMapping("/user/order/AddrInfoController")
public class shbAddrInfoController {
	@Reference
	private AddInfoService aService;
	@Reference
	 private AdminUserService adminUserService;
	//11
	@RequestMapping("/toaddAddr")
	public String toaddAddr(HttpServletRequest request)  {
		 
		 
		return "user/order/addAddr";
		
	}
	
	//11
	@RequestMapping("/add")
	public void add(HttpServletRequest request, HttpServletResponse response) throws  IOException {
		System.out.println("进入add");
		//User user = (User) request.getSession().getAttribute("user");
		String name = request.getParameter("name");
		String phone = request.getParameter("phone");
		String addr = request.getParameter("addr");
		///////////////////////////////int uid = user.getId();cookie中取出来
		// 0是非默认地址，1是默认地址
		User user=adminUserService.validnameAdd(CookieUtils.getCookie(request,"username"));

		aService.add(new AddrInfo(name, phone, addr, user.getId(), 0));
		/*
		 * response.sendRedirect(request.getContextPath() +
		 * "/AddrInfoServlet?m=select");
		 */
		response.getWriter().print(true);
	}
	//11
	@RequestMapping("/delete")
	public void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		aService.delete(id);
		response.getWriter().print(true);
	}
	//11
	@RequestMapping("/update")
	public void update(HttpServletRequest request, HttpServletResponse response) throws  IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		String phone = request.getParameter("phone");
		String addr = request.getParameter("addr");
		aService.update(new AddrInfo(id, name, phone, addr));
		response.getWriter().print(true);
	}
	//11
	@RequestMapping("/updateState")
	public void updateState(HttpServletRequest request, HttpServletResponse response)
			throws  IOException {
		int id = Integer.parseInt(request.getParameter("id"));
//	cookie中取出阿莱	User user = (User) request.getSession().getAttribute("user");
//		int uid = user.getId();
		User user=adminUserService.validnameAdd(CookieUtils.getCookie(request,"username"));

		aService.updateState(id, user.getId());
		response.getWriter().print(true);
	}
	//11
	@RequestMapping("/selectById")
	public String  selectById(HttpServletRequest request, HttpServletResponse response)
			throws  IOException {
		String isd = request.getParameter("id");
		int id = Integer.parseInt(isd);
		AddrInfo addrInfo = aService.selectOne(id);
		String[] split = addrInfo.getAddr().split("-");
		request.setAttribute("mprovince", split[0]);
		request.setAttribute("mcity", split[1]);
		request.setAttribute("mdistrict", split[2]);
		request.setAttribute("addr2", split[3]);
		request.setAttribute("addrInfo", addrInfo);
		return "user/order/updateAddr";

	}
	//11
	@RequestMapping("/selectMoRen")
	public String selectMoRen(HttpServletRequest request, HttpServletResponse response) throws  IOException{
		System.out.println("进入addr");
	/*	User user = (User) request.getSession().getAttribute("user");
		System.out.println(1);
		int uid = user.getId();*///cookie中取出来
		User user=adminUserService.validnameAdd(CookieUtils.getCookie(request,"username"));

		System.out.println(2);
		AddrInfo addrInfo=aService.selectMren(user.getId());
		System.out.println(3);
		System.out.println("查询默认地址"+addrInfo);
		System.out.println(4);
		request.setAttribute("addrInfo", addrInfo);
		System.out.println(5);
		return "/user/order/submitOrder";
 	}
	//11
	@RequestMapping("/select")
	public String select(HttpServletRequest request, HttpServletResponse response) throws  IOException {
		//User user = (User) request.getSession().getAttribute("user");
		//int uid = user.getId();
		User user=adminUserService.validnameAdd(CookieUtils.getCookie(request,"username"));

		List<AddrInfo> list = aService.select(user.getId());
		for (AddrInfo addrInfo : list) {
			System.out.println(addrInfo);
		}

		/* request.getSession().setAttribute("list", list); */
		request.setAttribute("user", user);
		request.setAttribute("list", list);
		return "user/order/myAddr";

	}
	@RequestMapping("/totest")
	public String totest(HttpServletRequest request, HttpServletResponse response) throws  IOException {
		request.getSession().setAttribute("user",new User(1, "", "", "", "", "", "", 0, "", ""));
		 
		return "redirect:/user/order/AddrInfoController/select";
		
	}
	
}
