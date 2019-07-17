package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @description:
 * @author: yangyh
 * @create: 2019-03-01 15:22
 **/
@Controller
public class HomeControlle {

    @RequestMapping("/index")
    public String index() {
        return "/index";
    }

    @RequestMapping(value = "/login")
    public String login() {
        return "login";
    }

}
