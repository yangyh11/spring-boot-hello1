package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * @description: 模板测试
 * @author: yangyh
 * @create: 2019-02-26 16:36
 **/
@Controller
public class TemplateController {

    @RequestMapping("/helloHtml")
    public String helloHtml(Map<String, Object> map){
        map.put("hello", "from TemplateController.helloHtml");
        return "/helloHtml";

    }

    @RequestMapping("/helloFtl")
    public String helloFtl(Map<String, Object> map) {
        map.put("hello", "from TemplateController.helloFtl");
        return "/helloFtl";
    }
}
