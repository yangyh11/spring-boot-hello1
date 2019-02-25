package org.springboot.sample.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @description: 静态资源映射 未生效，配置文件修改生效
 * @author: yangyh
 * @create: 2019-02-25 17:23
 **/
@Configuration
public class MyWebAppConfigurer extends WebMvcConfigurationSupport {

    //TODO 未生效，配置文件修改生效
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/my/**").addResourceLocations("classpath:/myres/");
//        super.addResourceHandlers(registry);
//    }
}
