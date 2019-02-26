package com.example.base.servlet;

        import com.alibaba.druid.support.http.WebStatFilter;

        import javax.servlet.annotation.WebFilter;
        import javax.servlet.annotation.WebInitParam;

/**
 * @description: druid过滤器
 * @author: yangyh
 * @create: 2019-02-26 14:24
 **/
@WebFilter(filterName = "druidWebStatFilter", urlPatterns = "/*",
        initParams = {
                @WebInitParam(name = "exclusions", value = "*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*")//忽略资源
        }
)
public class DruidStatFilter extends WebStatFilter {

}
