package com.example.base.scheduling;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;

/**
 * @description: 定时任务
 * @author: yangyh
 * @create: 2019-02-25 21:57
 **/
@Configuration
@EnableScheduling
public class ScheduingConfig {

    @Scheduled(cron = "0/20 * * * * ?")
    public void schedulerDemo(){
        System.out.println("我是一个定时任务~~~" + new Date());
    }
}
