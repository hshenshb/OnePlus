package com.tjetc;

import com.alibaba.dubbo.config.annotation.Reference;
import com.tjetc.service.AdminAdminService;
import com.tjetc.service.ProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OnepluscumApplicationTests {
    @Reference
    private AdminAdminService adminService;
    @Reference
    private ProductService pService;
    @Test
    public void contextLoads() {



    }

}
