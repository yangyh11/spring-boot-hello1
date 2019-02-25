package com.example.test.controller;

import com.example.test.entity.Demo;
import com.example.test.service.DemoService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @description:
 * @author: yangyh
 * @create: 2019-02-25 16:07
 **/
@RestController
@RequestMapping("/jpaDemo")
public class JpaDemoController {

    @Resource
    private DemoService demoService;

    @RequestMapping("/save")
    public String save(){
        Demo demo = new Demo();
        demo.setName("Angel");
        demoService.save(demo);
        return "数据保存成功！";
    }

    @RequestMapping("/getDemoById")
    public Demo getDemoById(long id) {
        Demo demo = demoService.getDemoById(id);
        return demo;
    }

}
