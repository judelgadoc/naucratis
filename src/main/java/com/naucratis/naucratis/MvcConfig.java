package com.naucratis.naucratis;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        WebMvcConfigurer.super.addResourceHandlers(registry);
        registry.addResourceHandler("/uploads/**").addResourceLocations("file:/C:/Temp/uploads/");
    }
}



