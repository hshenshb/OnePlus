package com.tjetc.controller;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tjetc.pojo.Admin;
import com.tjetc.service.AdminAdminService;

@Controller
@RequestMapping("/adminController")
public class adminController {
	@Reference
	private AdminAdminService adminService;
	
 
	@RequestMapping("/Login")
	private String Login(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.getSession().setAttribute("admin", null);
		return "Login";
	}
	@RequestMapping("/main")
	private String mian(HttpServletRequest request, HttpServletResponse response) throws IOException {
		return "/mainFilter/main";
	}
	@RequestMapping("/nOPower")
	private String nOPower(HttpServletRequest request, HttpServletResponse response) throws IOException {
		return "administrator/admin/nOPower";
	}
	@RequestMapping("/toadminAdd")
	private String toadminAdd(HttpServletRequest request, HttpServletResponse response) throws IOException {
		return "administrator/admin/addminAdd";
	}
	@RequestMapping("/welcome")
	private String main(HttpServletRequest request, HttpServletResponse response) throws IOException {
		return "welcome";
	}
	@RequestMapping("/validUsername")
	private void validUsername(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String username = request.getParameter("username");
		 
		Admin user = adminService.validUsername(username);
		System.out.println(username);
		response.getWriter().println(user != null);
	}
	@RequestMapping("/validUsernameUpdate")
	private void validUsernameUpdate(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String username = request.getParameter("username");
		int id = Integer.parseInt(request.getParameter("id"));
		 
		Admin user = adminService.validUsername(username);

		response.getWriter().println(user == null || user.getUsername().equals(adminService.getById(id).getUsername()));
	}
	@RequestMapping("/validUsernameAdd")
	private void validUsernameAdd(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String username = request.getParameter("username");

		 
		Admin user = adminService.validUsername(username);

		response.getWriter().println(user == null);
	}
	@RequestMapping("/validPassword")
	private void validPassword(HttpServletRequest request, HttpServletResponse response)
			throws IOException, NoSuchAlgorithmException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		 
		Admin user = adminService.validUsername(username);

		response.getWriter().println(user != null && user.getPassword().equals(password));
	}
	@RequestMapping("/login")
	private String login(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String username = request.getParameter("username");

		 
		Admin user = adminService.validUsername(username);
		System.out.println(user);

		request.getSession().setAttribute("admin", user);
		 return "/mainFilter/main";

	}


@RequestMapping("/list")
	private String list(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String name = request.getParameter("name") == null ? "" : request.getParameter("name");
		int page = request.getParameter("page")==null?1:Integer.parseInt(request.getParameter("page"));
		
		 
		 
		int pagesize = 5;

		 

		Map<String, Object> map = adminService.getAll(name,page);

		request.setAttribute("list", map.get("list"));
		request.setAttribute("name", name);
		request.setAttribute("pageInfo", map.get("pageInfo"));
		 
		return "administrator/admin/adminList";

	}
@RequestMapping("/add")
	private String add(HttpServletRequest request, HttpServletResponse response)
			throws IOException, NoSuchAlgorithmException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		int power = Integer.parseInt(request.getParameter("power"));
		Admin user = new Admin(username, password, email, power);
		 
		adminService.addAdmin(user);

		 return "redirect:/adminController/list";
	}
@RequestMapping("/update")
	private String update(HttpServletRequest request, HttpServletResponse response)
			throws IOException, NoSuchAlgorithmException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		int id = Integer.parseInt(request.getParameter("id"));
		String email = request.getParameter("email");
		int power = Integer.parseInt(request.getParameter("power"));
		Admin user = new Admin(id, username, password, email, power);
		 
		adminService.updateAdmin(user);
		return "redirect:/adminController/list";
 	}
@RequestMapping("/getById")
	private String getById(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		int id = Integer.parseInt(request.getParameter("upid"));

		 
		Admin user = adminService.getById(id);

		request.setAttribute("us", user);
		 return "administrator/admin/adminUpdate";

	}
@RequestMapping("/delete")
	private String delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int id = Integer.parseInt(request.getParameter("deid"));

		 
		adminService.delete(id);

		return "redirect:/adminController/list";
	}
}
