//package com.example.config.shiro;
//
//import UserInfoRepository;
//import UserInfo;
//import org.apache.shiro.authc.AuthenticationInfo;
//import org.apache.shiro.authc.AuthenticationToken;
//import org.apache.shiro.authc.SimpleAuthenticationInfo;
//import org.apache.shiro.util.ByteSource;
//
//import javax.annotation.Resource;
//
///**
// * @description: 身份校验核心类
// * @author: yangyh
// * @create: 2019-03-05 16:53
// **/
//public class MyShiroRealm {
//
//    @Resource
//    private UserInfoRepository userInfoRepository;
//
//    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token){
//        //获取用户输入的账户名
//        String userName = (String) token.getPrincipal();
//        UserInfo userInfo = userInfoRepository.findByUsername(userName);
//        if (userInfo == null) {
//            return null;
//        }
//        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(userInfo,userInfo.getPassword(), ByteSource.Util.bytes(userInfo.getSalt()))
//    }
//}
