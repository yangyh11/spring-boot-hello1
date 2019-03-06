package com.example.service.impl;

import com.example.service.HelloService;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: yangyh
 * @create: 2019-02-27 17:08
 **/
@Service
public class HelloServiceImpl implements HelloService {

    @Override
    public String getName() {
        return "hello";
    }
}
