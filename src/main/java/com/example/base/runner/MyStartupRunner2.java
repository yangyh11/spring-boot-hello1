package com.example.base.runner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Spring Boot应用程序在启动后，会遍历CommandLineRunner接口的实例并运行它们的run方法。也可以利用
 * @Order注解（或者实现Order接口）来规定所有CommandLineRunner实例的运行顺序。
 *
 * @description: 服务启动执行
 * @author: yangyh
 * @create: 2019-02-27 15:53
 **/
@Component
@Order(value = 1)
public class MyStartupRunner2 implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        System.out.println(">>>>>>>>>>>>>>>服务启动执行，执行加载数据等操作222222<<<<<<<<<<<<<");
    }
}
