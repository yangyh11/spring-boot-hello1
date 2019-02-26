package com.example.sample.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @description: 静态资源映射 未生效，配置文件修改生效。未生效原因是没有放到启动类下面，继承WebMvcConfigurationSupport重写
 *               addResourceHandlers方法，会覆盖默认静态资源映射,把静态资源也加上.
 *               WebMvcConfigurerAdapter该类已失效，推荐使用WebMvcConfigurationSupport
 * @author: yangyh
 * @create: 2019-02-25 17:23
 **/
@Configuration
public class MyWebAppConfigurer extends WebMvcConfigurationSupport {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //默认静态资源映射
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/META-INF/resources/")
                .addResourceLocations("classpath:/resources/")
                .addResourceLocations("classpath:/static/")
                .addResourceLocations("classpath:/public/");
        //自定义静态资源映射
        registry.addResourceHandler("/my/**")
                .addResourceLocations("classpath:/myres/")
                .addResourceLocations("file:D:/test/");
        super.addResourceHandlers(registry);
    }
}
