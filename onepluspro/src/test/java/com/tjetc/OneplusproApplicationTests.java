package com.tjetc;

import com.tjetc.mapper.AdminAdminMapper;
import com.tjetc.mapper.AdminProductMapper;
import com.tjetc.mapper.OrderMapper;
import com.tjetc.pojo.Admin;
import com.tjetc.pojo.Orders;
import com.tjetc.pojo.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OneplusproApplicationTests {
    @Autowired
    private AdminAdminMapper aMapper;
    @Autowired
    private AdminProductMapper pMapper;
    @Autowired
    private OrderMapper oMapper;
    @Test
    public void contextLoads() {

        //aMapper.addAdmin(new Admin(4,"你好", "7777777","1145859876@qq.com",0));
       // Product product=new Product("1", 1,4, 7, 1, "1","1","1", "1", 1, 1,"1997-07-01");
       // pMapper.add(product);
        Orders orders=new Orders("3836069227AD4561A6DF423EB16B5C4C","阿卡丽","13755962174","天津市-天津城区-滨海新区-华为p30",1,3447,"2019-08-09 14:50:25.429",1);
        oMapper.add(orders);
        /*id=3836069227AD4561A6DF423EB16B5C4C, oname=阿卡丽, phone=13755962174, addr=天津市-天津城区-滨海新区-华为p30, usid=1, total=3447.0, ordertime=2019-08-09 14:50:25.429, state=1*/
    }

}
