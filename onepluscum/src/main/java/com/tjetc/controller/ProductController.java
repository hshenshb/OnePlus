package com.tjetc.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.tjetc.pojo.Category;
import com.tjetc.pojo.Product;
import com.tjetc.service.AdminCategoryService;
import com.tjetc.service.AdminSecondCategoryService;
import com.tjetc.service.ProductService;
import com.tjetc.service.QTProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/goodspage")
public class ProductController {
    @Reference
    private QTProductService qtProductService;
    @Reference
    private AdminCategoryService acService;
    @Reference
    private ProductService pService;
    @Reference
    AdminSecondCategoryService aSecondCservice;
    @RequestMapping("/tets")
    private String tets(){
        System.out.println("tets");
        return "/user/goodspage/goodspage";
    }

    @RequestMapping("/list")
    private String list(Model m, String curPage, String name){
        // 分页
        Product p=new Product();
        name = name == null ? "" : name;
        p.setPname(name);

        int page=curPage==null?1:Integer.parseInt(curPage);



        Map<String,Object> map = qtProductService.likeList(name,page);

        m.addAttribute("list", map.get("list"));
        m.addAttribute("p", p);
        m.addAttribute("pageInfo", map.get("pageInfo"));
        List<Product> listAll = qtProductService.list();
        m.addAttribute("listAll", listAll);
        // 一级二级分类列表
        List<Category> categories = acService.getTrueAll();
        for (Category category:categories) {
            category.setSecondcategories(aSecondCservice.getByCid(category.getId()));
        }
        m.addAttribute("categories", categories);
        return "/user/goodspage/goodspage";
    }
    @RequestMapping("/list2")
    private String list2(Model m,int cid){
        // 一级二级分类列表
        List<Category> categories = acService.getTrueAll();
        for (Category category:categories) {
            category.setSecondcategories(aSecondCservice.getByCid(category.getId()));
        }
        m.addAttribute("categories", categories);
        // 对应的img
        Category c = acService.getById(cid);
        m.addAttribute("c", c);
        // 对应cid的商品信息
        List<Product> list = qtProductService.listByCid(cid);
        m.addAttribute("list", list);

        return "/user/goodspage/goodspage2";
    }
    @RequestMapping("/list3")
    private String list3(Model m,int scid){
        // 一级二级分类列表
        List<Category> categories = acService.getTrueAll();
        m.addAttribute("categories", categories);
        // 对应的img
        Category c = acService.getByScid(scid);
        m.addAttribute("c", c);
        // 对应scid的商品信息
        System.out.println(scid);
        List<Product> list = qtProductService.listBySCid(scid);
        m.addAttribute("list", list);
        for (Product product : list) {
            System.out.println(product);
        }
        return "/user/goodspage/goodspage2";
    }
    @RequestMapping("/goodsPage")
    private String goodsPage(Model m,int pid){
        // 所有商品信息
        System.out.println(pid);
        Product p = pService.getById(pid);
        m.addAttribute("p", p);
        return "/user/goodspage/index";
    }

    @RequestMapping("/toIndex")
    private String toIndex(){

        return "/user/shouye";
    }

}
