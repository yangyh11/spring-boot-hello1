package com.example.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: yangyh
 * @create: 2019-02-25 11:32
 **/
@RestController
public class HelloController {

    @RequestMapping("/")
    public String hello(){
        return "hello world";
    }
}
