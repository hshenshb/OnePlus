package com.tjetc;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.tjetc.mapper")
@EnableDubbo
public class OneplusproApplication {

    public static void main(String[] args) {
        SpringApplication.run(OneplusproApplication.class, args);
    }

}
