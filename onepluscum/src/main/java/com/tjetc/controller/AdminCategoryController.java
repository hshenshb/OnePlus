package com.tjetc.controller;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.tjetc.pojo.Category;
import com.tjetc.service.AdminCategoryService;

@Controller
@RequestMapping("/AdminCategoryController")
public class AdminCategoryController {
	@Reference
	 private AdminCategoryService acService;
	@Value("${fastdfs_ip}")
	private String fastdfs_ip;
	@RequestMapping("/toAddCategory")
	private String toAddCategory(HttpServletRequest request, HttpServletResponse response) {
		return "administrator/category/categoryAdd";
		
	}
	
	@RequestMapping("/validCategoryUpdate")
	private void validCategoryUpdate(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String name = request.getParameter("name");
		int  id =Integer.parseInt( request.getParameter("id"));
		Category category=acService.getByName(name);

		response.getWriter().println(category==null||category.getCname().equals(acService.getById(id).getCname()));
	}
	@RequestMapping("/list")
	private String list(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String name = request.getParameter("name")==null?"":request.getParameter("name");
		int page = request.getParameter("page")==null?1:Integer.parseInt(request.getParameter("page"));
		
		Map<String,Object> map=acService.getAll(name,page);
		
		request.setAttribute("list", map.get("list"));
		request.setAttribute("pageInfo", map.get("pageInfo"));
		request.setAttribute("name", name);
		 return "administrator/category/categoryList";
		 
	 
	}
	@RequestMapping("/add")
	private String add(HttpServletRequest request,HttpServletResponse response) throws IOException {
		String name = request.getParameter("name");
		MultipartHttpServletRequest mRequest=(MultipartHttpServletRequest) request;
		MultipartFile file = mRequest.getFile("photo");
		String photo=null;
		if(file!=null&&file.getSize()>0) {
			if(file.getOriginalFilename()!=null&&!file.getOriginalFilename().equals("")) {
				//String realPath = request.getContextPath()+"/uplode/";
				//获取当前项目的路径并且创建uplode文件夹
				String realPath = new File("").getCanonicalPath()+"/uplode/";
				File dir=new File(realPath);

				if(!dir.exists()) {
					dir.mkdirs();
				}
				File realfile=new File(dir, file.getOriginalFilename());
				try {
					file.transferTo(realfile);
					photo="uplode/"+file.getOriginalFilename();
				} catch (IllegalStateException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		 
		  
		 Category category=new Category(name,photo);
		 acService.add(category);
		 return "redirect:/AdminCategoryController/list";
 	 
	} 
	@RequestMapping("/validnameAdd")
	private void validnameAdd(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String  name = request.getParameter("name");
		 
		Category category=acService.validname(name);
		 
		response.getWriter().println(category==null);
	}
	@RequestMapping("/getById")
	private String getById(HttpServletRequest request, HttpServletResponse response) throws IOException{
		int  id =Integer.parseInt( request.getParameter("upid"));
	 
		 
		Category category=acService.getById(id);
		  
		request.setAttribute("category", category);
		 
		 return "administrator/category/categoryUpdate";
	}
	@RequestMapping("/update")
	private String  update(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String  name = request.getParameter("name");
		  
		int  id =Integer.parseInt( request.getParameter("id"));
		 MultipartHttpServletRequest mRequest=(MultipartHttpServletRequest) request;
		 MultipartFile file = mRequest.getFile("photo");
		 String photo=null;
		if(file!=null&&file.getSize()>0) {
			if(file.getOriginalFilename()!=null&&!file.getOriginalFilename().equals("")) {
				//String realPath = request.getContextPath()+"/uplode/";
				//获取当前项目的路径并且创建uplode文件夹
				String realPath = new File("").getCanonicalPath()+"/uplode/";
				File dir=new File(realPath);
				System.out.println(realPath);
				if(!dir.exists()) {
					dir.mkdirs();
				}
				File realfile=new File(dir, file.getOriginalFilename());
				try {
					file.transferTo(realfile);
					photo="uplode/"+file.getOriginalFilename();
				} catch (IllegalStateException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		 
		 Category category=new Category(id, name,photo);
		 
		acService.updatecategory(category);
		
		return "redirect:/AdminCategoryController/list";
		 
	}
	@RequestMapping("/delete")
	private String  delete(HttpServletRequest request, HttpServletResponse response) throws IOException{
		int  id =Integer.parseInt( request.getParameter("deid"));
		
		
		 
		acService.delete(id);
		
		 
		return "redirect:/AdminCategoryController/list";
		 
	}
	
}
