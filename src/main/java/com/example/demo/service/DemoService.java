package com.example.demo.service;

import com.example.demo.entity.Demo;

/**
 * @description:
 * @author: yangyh
 * @create: 2019-02-25 16:02
 **/
public interface DemoService {

    void save(Demo demo);

    Demo getDemoById(long id);
}
