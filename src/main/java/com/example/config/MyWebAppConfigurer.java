package com.example.config;

import com.example.base.interceptor.MyInterceptor1;
import com.example.base.interceptor.MyInterceptor2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.charset.Charset;
import java.util.List;


/**
 * @description: 静态资源映射 未生效，配置文件修改生效。未生效原因是没有放到启动类下面，继承WebMvcConfigurationSupport重写
 *               addResourceHandlers方法，会覆盖默认静态资源映射,把静态资源也加上.
 *               WebMvcConfigurerAdapter该类已失效，推荐使用WebMvcConfigurationSupport
 *               spring boot 继承WebMvcConfigurationSupport后自动配置不生效问题,想要使用自动配置生效，又要按自己的需要重写某些方法，
 *               则可以自己的配置类可以继承  WebMvcConfigurerAdapter 这个类。不过在spring5.0版本后这个类被丢弃了 WebMvcConfigurerAdapter
 *               解决方法：自定义配置类中直接实现WebMvcConfigurer 接口就好了
 * @author: yangyh
 * @create: 2019-02-25 17:23
 **/
@Configuration
public class MyWebAppConfigurer implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //默认静态资源映射,继承WebMvcConfigurer接口后，默认配置不会被覆盖
//        registry.addResourceHandler("/**")
//                .addResourceLocations("classpath:/META-INF/resources/")
//                .addResourceLocations("classpath:/resources/")
//                .addResourceLocations("classpath:/static/")
//                .addResourceLocations("classpath:/public/");
        //自定义静态资源映射
        registry.addResourceHandler("/my/**")
                .addResourceLocations("classpath:/myres/")
                .addResourceLocations("file:D:/test/");
    }

    /**强调一点：只有经过DispatcherServlet 的请求，才会走拦截器链，我们自定义的Servlet 请求是不会被拦截的
     * 比如我们自定义的Servlet地址http://localhost:8080/myServlet1 是不会被拦截器拦截的
    ***/
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //多个拦截器组成一个拦截器链 执行顺序122121
        // addPathPatterns 用于添加拦截规则
        // excludePathPatterns 用于排除拦截
        registry.addInterceptor(new MyInterceptor1()).addPathPatterns("/**");
        registry.addInterceptor(new MyInterceptor2()).addPathPatterns("/**");
    }

    //TODO 解决中文乱码问题，继承WebMvcConfigurationSupport后增加此配置，
    // 会导致org.springframework.http.converter.HttpMessageNotWritableException:异常
    // 解决方法:实现WebMvcConfigurer接口
    @Bean
    public HttpMessageConverter<String> responseBodyConverter() {
        StringHttpMessageConverter converter = new StringHttpMessageConverter(
                Charset.forName("UTF-8"));
        return converter;
    }

    @Override
    public void configureMessageConverters(
            List<HttpMessageConverter<?>> converters) {
        converters.add(responseBodyConverter());
    }

    @Override
    public void configureContentNegotiation(
            ContentNegotiationConfigurer configurer) {
        configurer.favorPathExtension(false);
    }
}
