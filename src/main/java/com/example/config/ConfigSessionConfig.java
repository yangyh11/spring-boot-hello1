package com.example.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * @description: 分布式session保存redis
 * @author: yangyh
 * @create: 2019-03-01 13:34
 **/
@Configuration
@EnableRedisHttpSession
public class ConfigSessionConfig {

}
