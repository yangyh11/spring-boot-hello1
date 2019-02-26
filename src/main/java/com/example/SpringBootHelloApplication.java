package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**其中@SpringBootApplication申明让spring boot自动给程序进行必要的配置，等价于以默认属性使用
    @Configuration，@EnableAutoConfiguration和@ComponentSca
 **/
@SpringBootApplication
@ServletComponentScan
public class SpringBootHelloApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootHelloApplication.class, args);
    }

}
