package com.config;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Component
public class WebMvcConfig extends WebMvcConfigurerAdapter{

    /**
     * 重写web静态资源的访问路径
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("file:///C:/Users/dongjj/Desktop/Torison/com.torison.Parent/com.torison.web/src/main/resources/static/");
    }
}
