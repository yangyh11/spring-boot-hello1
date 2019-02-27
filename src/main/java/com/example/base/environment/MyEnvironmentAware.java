package com.example.base.environment;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * @description: 获取环境变量信息
 * @author: yangyh
 * @create: 2019-02-27 16:01
 **/
@Configuration
public class MyEnvironmentAware implements EnvironmentAware {

    @Value("${spring.datasource.url}")
    private String myUrl;

    @Override
    public void setEnvironment(Environment environment) {

        //打印注入的属性信息.
        System.out.println("myUrl=" + myUrl);
        //通过 environment 获取到系统属性.
        System.out.println(environment.getProperty("JAVA_HOME"));
        //通过 environment 同样能获取到application.properties配置的属性.
        System.out.println(environment.getProperty("spring.datasource.url"));

    }
}
