package com.tjetc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class OnepluscumApplication {

    public static void main(String[] args) {
        SpringApplication.run(OnepluscumApplication.class, args);
    }

}
