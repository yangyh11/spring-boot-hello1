package com.example.test.service.impl;

import com.example.test.dao.DemoRepository;
import com.example.test.entity.Demo;
import com.example.test.service.DemoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @description:
 * @author: yangyh
 * @create: 2019-02-25 16:03
 **/
@Service
public class DemoServiceImpl implements DemoService {

    @Resource
    private DemoRepository demoRepository;

    @Override
    public void save(Demo demo) {

        demoRepository.save(demo);
    }
}