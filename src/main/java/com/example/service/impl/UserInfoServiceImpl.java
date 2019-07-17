package com.example.service.impl;

import com.example.dao.UserInfoRepository;
import com.example.entity.UserInfo;
import com.example.service.UserInfoService;

import javax.annotation.Resource;

/**
 * @description:
 * @author: yangyh
 * @create: 2019-03-05 16:51
 **/
public class UserInfoServiceImpl implements UserInfoService {

    @Resource
    private UserInfoRepository userInfoRepository;
    /**
     * 通过username查找用户信息;
     *
     * @param username
     */
    @Override
    public UserInfo findByUsername(String username) {
        System.out.println("UserInfoServiceImpl.findByUsername()");
        return userInfoRepository.findByUsername(username);
    }
}
