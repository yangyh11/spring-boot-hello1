package com.example.springboothello1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class SpringBootHello1Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootHello1Application.class, args);
    }

    @RequestMapping("/")
    public String hello(){

        return "hello";
    }

}
