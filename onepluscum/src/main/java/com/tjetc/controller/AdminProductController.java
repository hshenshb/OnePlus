package com.tjetc.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.tjetc.pojo.Product;
import com.tjetc.pojo.SecondCategory;
import com.tjetc.service.AdminSecondCategoryService;
import com.tjetc.service.ProductService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/AdminProductController")
public class AdminProductController  {
    @Reference
    private ProductService pService;
    @Reference
    private AdminSecondCategoryService scService;
    @Value("${fastdfs_ip}")
    private String fastdfs_ip;
    @RequestMapping("/list")
    public String list(HttpServletRequest request, HttpServletResponse response){
        String name=request.getParameter("name")==null?"":request.getParameter("name");
        int page=Integer.parseInt(request.getParameter("page")==null?"1":request.getParameter("page"));


        Map<String,Object> map = pService.list(name,page);

        request.setAttribute("name",name);
        request.setAttribute("list",map.get("list"));
        request.setAttribute("pageInfo",map.get("pageInfo"));
        return "/administrator/product/productList";
     }
     @RequestMapping("/toAdd")
    public String toAdd(HttpServletRequest request, HttpServletResponse response)  {

        List<SecondCategory> list=scService.getTrueall();
        request.setAttribute("list",list);
        return "/administrator/product/addProduct";
     }
     @RequestMapping("/getById")
    public String getById(HttpServletRequest request, HttpServletResponse response) {
        int id=Integer.parseInt(request.getParameter("upid"));

        Product product=pService.getById(id);
        List<SecondCategory> list=scService.getTrueall();
        request.setAttribute("list",list);
        request.setAttribute("product",product);
        return "/administrator/product/productUpdate";
     }
     @RequestMapping("/add")
    public String add(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name=request.getParameter("pname");
        String discrip=request.getParameter("discrip");
        double price=Double.parseDouble(request.getParameter("price"));
        double preprice=Double.parseDouble(request.getParameter("preprice"));
        int scid=Integer.parseInt(request.getParameter("scid"));
        int cid=scService.getById(scid).getCid();
        int ishot=Integer.parseInt(request.getParameter("ishot"));
        int leftover=Integer.parseInt(request.getParameter("leftover"));
        MultipartRequest mRequest= (MultipartRequest) request;
        MultipartFile file = mRequest.getFile("photopathh");
        MultipartFile file1 = mRequest.getFile("photopathh2");
        MultipartFile file2 = mRequest.getFile("photopathh3");
        String photo=uplodePhoto(file,request);
        String photo1=uplodePhoto(file1,request);
        String photo2=uplodePhoto(file2,request);
         Date date = new Date();
         SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
         String format = dateFormat.format(date);

        Product product=new Product(name, price,cid, scid, ishot, photo,photo1,photo2, discrip, leftover, preprice,format);
         System.out.println(product);
        pService.add(product);
        return "redirect:/AdminProductController/list";


    }
    @RequestMapping("/delete")
    public String delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int id=Integer.parseInt(request.getParameter("deid"));

        pService.delete(id);
        return "redirect:/AdminProductController/list";
    }
    @RequestMapping("/update")
    public String update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name=request.getParameter("pname");
        String discrip=request.getParameter("discrip");
        double price=Double.parseDouble(request.getParameter("price"));
        double preprice=Double.parseDouble(request.getParameter("preprice"));
        int scid=Integer.parseInt(request.getParameter("scid"));
        int cid=scService.getById(scid).getCid();
        int id=Integer.parseInt(request.getParameter("id"));
        int ishot=Integer.parseInt(request.getParameter("ishot"));
        int leftover=Integer.parseInt(request.getParameter("leftover"));
        MultipartRequest mRequest= (MultipartRequest) request;
        MultipartFile file = mRequest.getFile("photopathh");
        MultipartFile file1 = mRequest.getFile("photopathh2");
        MultipartFile file2 = mRequest.getFile("photopathh3");
        String photo=null;
        String photo1=null;
        String photo2=null;
        if(file.getOriginalFilename()!=null&&!file.getOriginalFilename().equals("")){
            photo=uplodePhoto(file,request);
        }else {
            photo=pService.getById(id).getPhotopath();
        }
        if(file1.getOriginalFilename()!=null&&!file1.getOriginalFilename().equals("")){
            photo1=uplodePhoto(file1,request);
        }else {
            photo1=pService.getById(id).getPhotopath2();
        }
        if(file2.getOriginalFilename()!=null&&!file2.getOriginalFilename().equals("")){
            photo2=uplodePhoto(file2,request);
        }else {
            photo2=pService.getById(id).getPhotopath3();
        }


        Product product=new Product(id,name, price,cid, scid, ishot, photo,photo1,photo2, discrip, leftover, preprice);
        pService.update(product);
        return "redirect:/AdminProductController/list";

    }

    //上传图片
    private String uplodePhoto(MultipartFile file,HttpServletRequest request) throws IOException {
        String photo=null;
        if(file!=null&&file.getSize()>0) {
            if(file.getOriginalFilename()!=null&&!file.getOriginalFilename().equals("")) {
                //String realPath = request.getContextPath()+"/uplode/";
                //获取当前项目的路径并且创建uplode文件夹
                String realPath = new File("").getCanonicalPath()+"/uplode/";
                File dir=new File(realPath);
                System.out.println(realPath+"12222222222222222222222222");
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
        return photo;
    }


}
