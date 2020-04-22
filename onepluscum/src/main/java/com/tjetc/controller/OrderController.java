package com.tjetc.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tjetc.config.CookieUtils;
import com.tjetc.pojo.*;
import com.tjetc.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.*;

@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private RedisTemplate ObjectRedisTemplate;
    @Reference
    AddInfoService addrinfoservise;
    @Reference
    OrderService orderService;
    @Reference
    OrderItemService orderItemService;
    @Reference
    ProductService productService;
    @Reference
    AdminUserService adminUserService;
    @Reference
    private AdminOrderService adminOrderService;
    @Autowired
    shbcartController shbcartController;

    // 前台 跳转submitOrder
    @RequestMapping("/toSubmitOrder")
    public String toSubmitOrder(HttpServletRequest request, Model model,HttpServletResponse response) {
        // ==================
        // 获取用户信息 地址

        // User user = (Users) request.getSession().getAttribute("user");

        User user=adminUserService.validnameAdd(CookieUtils.getCookie(request,"username"));

        System.out.println(user);
        /*
         * AddrInfo addrInfo = new AddrInfo(1, "test", "123123123", "dadsadsa", 1, 1);
         */
        System.out.println("userid=" + Integer.parseInt(user.getId() + ""));
        AddrInfo addrInfo = addrinfoservise.selectMren(user.getId());
        // 获取购物车中的商品

        Cookie[] cookies = request.getCookies();
        Cart cart = new Cart();
        boolean b = false;
        String decode = "";
        for (Cookie cookie : cookies) {
            decode = cookie.getName();
            System.out.println(decode);
            if (decode.equals("cuid") && !cookie.getValue().equals("")) {
                ValueOperations forValue = ObjectRedisTemplate.opsForValue();
                System.out.println(cookie.getValue());
                Object o = (Object) forValue.get(cookie.getValue());
                String s = JSON.toJSONString(o);
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

            }}

            // ==================
            model.addAttribute("addrInfo", addrInfo);
            model.addAttribute("cart", cart);
        
            return "user/order/submitOrder";
        }
    @RequestMapping("/list")
    public String list(HttpServletRequest request, Model model) {
        int page = request.getParameter("page")==null?1:Integer.parseInt(request.getParameter("page"));

        String oid = request.getParameter("oid");
        oid = oid == null ? "" : oid;
      /*  // 获取当前用户名
        User u = (User) request.getSession().getAttribute("user");
        int userid = u.getId();*///cookie取出来


        User user=adminUserService.validnameAdd(CookieUtils.getCookie(request,"username"));


        Map<String,Object> map = orderService.list(page,oid,user.getId());
        List<Orders> orders= (List<Orders>) map.get("list");

        // 遍历订单，加入订单项
        for (Orders order : orders) {

            List<OrderItem> items = orderItemService.getByOrderId(order.getId());
            // 遍历订单项集合，项订单项中加入product
            for (OrderItem orderItem : items) {
                orderItem.setP(productService.getById(orderItem.getPid()));
                order.getOrderItems().add(orderItem);
            }
        }
        boolean isNull = false;

            if (orders.size() == 0) {
                System.out.println("集合是空的");
                isNull = true;
            }
        System.out.println(orders+"*********************");
        request.setAttribute("pageInfo", map.get("pageInfo"));
        request.setAttribute("oid", oid);
        request.setAttribute("orders", orders);
        request.setAttribute("isNull", isNull);
        request.setAttribute("user",adminUserService.getById(user.getId()) );

        return "user/order/myorder";
    }

    // 前台 跳转userPay
    @RequestMapping("/toUserPay")
    public String toUserPay(HttpServletRequest request,HttpServletResponse response, int addrId, Model model) {
        // ====================
        // 根据addrid获取addr信息
        AddrInfo addrInfo = addrinfoservise.selectOne(addrId);
        // 生成orders
        Orders order = new Orders();
        // 生成orderid
        // 时间
        Date date = new Date();
        Timestamp nousedate = new Timestamp(date.getTime());
        order.setOrdertime(nousedate.toString());
        String id = UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
        order.setId(id);
        // cookie中获取
       // User user = (Users) request.getSession().getAttribute("user");
        User user=adminUserService.validnameAdd(CookieUtils.getCookie(request,"username"));
        order.setUsid(user.getId());
        /* order.setUsid(Long.parseLong("1")); */
        order.setOname(addrInfo.getAname());
        order.setAddr(addrInfo.getAddr());
        order.setPhone(addrInfo.getPhone());
        /*
         * // 从session中获取cart Cart cart = (Cart)
         * request.getSession().getAttribute("cart"); if (cart == null) {
         * response.sendRedirect(request.getContextPath() + "/user/cart/cart.jsp");
         * return; }
         */

        Cookie[] cookies = request.getCookies();
        Cart cart = new Cart();
        boolean b = false;
        String decode = "";
        for (Cookie cookie : cookies) {
            decode = cookie.getName();
            System.out.println(decode);
            if (decode.equals("cuid") && !cookie.getValue().equals("")) {
                ValueOperations forValue = ObjectRedisTemplate.opsForValue();
                System.out.println(cookie.getValue());
                Object o = (Object) forValue.get(cookie.getValue());
                String s = JSON.toJSONString(o);
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

            }}



        System.out.println("cart===============");
        System.out.println(cart.getTotal());
        double total = cart.getTotal();
        long l = new Double(total).longValue();
        System.out.println("转换后的long"+l);
        System.out.println(cart);
        // 1是未付款
        order.setState(1);
        // 设置total
        order.setTotal(new Double(cart.getTotal()).longValue());
        // order.setTotal(Long.parseLong("122"));
        // 把购物车中的商品添加到订单项

        // ==

        Collection<CartItem> cartItems = cart.getCartItems();
        List<OrderItem> orderitems = new ArrayList<>();
        for (CartItem cartItem : cartItems) {
            OrderItem orderItem = new OrderItem();
            orderItem.setCounts(cartItem.getCount() );
            orderItem.setOrderid(id);
            orderItem.setSubtotal(cartItem.getSubTotal() );
            orderItem.setPid(cartItem.getProduct().getId());
            /*
             * Orderitem orderItem = new
             * Orderitem(,Long.parseLong(cartItem.getProduct().getId()+"") , id);
             */
            orderitems.add(orderItem);
        }

        // =
        /*
         * Orderitem orderitem1 = new Orderitem(Long.parseLong("2"), "123",
         * Long.parseLong("2"), id, null); Orderitem orderitem2 = new
         * Orderitem(Long.parseLong("2"), "123", Long.parseLong("2"), id, null);
         */

        order.setOrderItems(orderitems);
        // 存到数据库
        System.out.println("**********"+orderitems);
        System.out.println("#########"+order);

        orderService.add(order);
        orderItemService.addOrderItems(orderitems);
        shbcartController.update(new Cart(),request,response);
        return "redirect:/order/list";

    }
    @RequestMapping("/ordertoUserPay")
    public String ordertoUserPay(HttpServletRequest request, Model model) {
        String oid = request.getParameter("oid");
        oid = oid == null ? "" : oid;
        adminOrderService.send2(oid);
        return "redirect:/order/list";
    }
    @RequestMapping("/querenshouhuo")
    public String querenshouhuo(HttpServletRequest request, Model model) {
        String oid = request.getParameter("oid");
        oid = oid == null ? "" : oid;
        adminOrderService.send3(oid);
        return "redirect:/order/list";
    }
    @RequestMapping("/quxiao")
    public String quxiao(HttpServletRequest request, Model model) {
        String oid = request.getParameter("oid");
        oid = oid == null ? "" : oid;
        adminOrderService.send5(oid);
        return "redirect:/order/list";
    }
    @RequestMapping("/delOrder")
    public String delOrder(HttpServletRequest request, Model model) {
        String oid = request.getParameter("oid");
        oid = oid == null ? "" : oid;

        adminOrderService.delOrder(oid);
        return "redirect:/order/list";
    }

    }
