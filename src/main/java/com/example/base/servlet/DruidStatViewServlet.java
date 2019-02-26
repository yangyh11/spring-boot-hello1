package com.example.base.servlet;

import com.alibaba.druid.support.http.StatViewServlet;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

/**
 * @description: druid数据源状态监控
 * @author: yangyh
 * @create: 2019-02-26 13:59
 **/
/**
 * 在web.xml中配置方式
 * <servlet>
 *     <servlet-name>DruidStatViewServlet</servlet-name>
 *     <servlet-class>com.alibaba.druid.support.http.StatViewServlet</servlet-class>
 *     <init-param>
 *          <param-name>allow</param-name>
 *          <param-value>128.242.127.1/24,128.242.128.1</param-value>
 *     </init-param>
 *     <init-param>
 *          <param-name>deny</param-name>
 *          <param-value>128.242.127.4</param-value>
 *     </init-param>
 *     <init-param>
 *          <param-name>loginUsername</param-name>
 *          <param-value>admin</param-value>
 *      </init-param>
 *      <init-param>
 *          <param-name>loginPassword</param-name>
 *          <param-value>123456</param-value>
 *      </init-param>
 *      <init-param>
 *         <param-name>resetEnable</param-name>
 *         <param-value>false</param-value>
 *     </init-param>
 * </servlet>
 * <servlet-mapping>
 *       <servlet-name>DruidStatViewServlet</servlet-name>
 *       <url-pattern>/druid/*</url-pattern>
 * </servlet-mapping>
 *
 **/



@WebServlet(urlPatterns = "/druid/*",
        initParams = {
                //ip白名单，没有配置或配置为空，则允许所有人访问
                @WebInitParam(name = "allow", value = "127.0.0.1"),
                //ip黑名单，存在共同时，deny优先于allow
                @WebInitParam(name = "deny", value = "192.168.1.31"),
                //用户名
                @WebInitParam(name = "loginUsername", value = "admin"),
                //密码
                @WebInitParam(name = "loginPassword", value = "123456"),
                //禁用HTML页面上的"Reset All"功能
                @WebInitParam(name = "resetEnable", value = "false")
        }
)


public class DruidStatViewServlet extends StatViewServlet {
    private static final long serialVersionUID = 1L;
}
