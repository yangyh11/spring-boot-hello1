package com.example.test.web;

import com.example.test.bean.Demo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: yangyh
 * @create: 2019-02-25 13:24
 **/
@RestController
@RequestMapping("/demo")
public class DemoController {

    @RequestMapping("/getDemo")
    public Demo getDemo(){

        Demo demo = new Demo();
        demo.setId(12);
        demo.setName("张三");
        return demo;
    }
}
