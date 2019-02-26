package com.example.base.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @description: 普通类调用Spring bean对象
 * @author: yangyh
 * @create: 2019-02-26 15:58
 **/
@Component
public class SpringContextUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext;


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextUtil.applicationContext = applicationContext;
    }

    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public static Object getBean(Class T) {
        try {
            return applicationContext.getBean(T);
        } catch (BeansException e) {
            return null;
        }
    }

    public static Object getBean(String name) {
        try {
            return applicationContext.getBean(name);
        } catch (BeansException e) {
            return null;
        }
    }

    public static Object getBean(String name, Class T) {
        try {
            return applicationContext.getBean(name, T);
        } catch (BeansException e) {
            return null;
        }

    }
}
