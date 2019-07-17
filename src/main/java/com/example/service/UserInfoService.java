package com.example.service;

import com.example.entity.UserInfo;

/**
 * @description:
 * @author: yangyh
 * @create: 2019-03-05 16:47
 **/
public interface UserInfoService {

    /**
     * 通过username查找用户信息;
     */
    UserInfo findByUsername(String username);
}
