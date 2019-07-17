package com.example.dao;

import com.example.entity.UserInfo;
import org.springframework.data.repository.CrudRepository;

/**
 * @description: UserInfo持久化类
 * @author: yangyh
 * @create: 2019-03-05 16:41
 **/
public interface UserInfoRepository extends CrudRepository<UserInfo, Long> {

    /**
     * 通过username查找用户信息;
     */
    UserInfo findByUsername(String username);
}
