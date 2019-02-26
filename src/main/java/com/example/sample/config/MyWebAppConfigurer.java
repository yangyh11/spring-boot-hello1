package com.example.sample.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @description: 静态资源映射 未生效，配置文件修改生效。未生效原因是没有放到启动类下面，继承WebMvcConfigurationSupport重写
 *               addResourceHandlers，会覆盖默认静态资源映射
 * @author: yangyh
 * @create: 2019-02-25 17:23
 **/
@Configuration
public class MyWebAppConfigurer extends WebMvcConfigurerAdapter {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/my/**").addResourceLocations("classpath:/myres/","file:D:/test/");
        super.addResourceHandlers(registry);
    }
}
