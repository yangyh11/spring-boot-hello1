package com.example.service;

import com.example.SpringBootHelloApplication;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@WebAppConfiguration
@SpringBootTest(classes = SpringBootHelloApplication.class)
public class HelloServiceTest {

    @Resource
    private HelloService helloService;

    @Resource
    private IpService ipService;
    @Test
    public void testGetName() {
        Assert.assertEquals("hello", helloService.getName());
    }


    @Test
    public void testIpService() {
        ipService.updteLocatioByExcel();
    }



}